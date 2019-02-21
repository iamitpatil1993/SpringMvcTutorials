package com.example.mvc.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>This controller's job is to handle http error codes in application and send custom error page with additional data.</p>
 * <p>We can map Exception classes to Exception handlers using @ExceptionHandler in controller class or in @ControllerAdvise class.</p>
 * <p>But, I found no standard way or spring featuer to map HTTP error codes to 'CUSTOM ERROR PAGES'. So, we can use servlet 3.0 solution for it.<p>
 * <p>We can use <error-pages> in web.xml to map http error code to static html pages or jsp pages BUT, if we do that, it will be static and
 * we won't be able to pass any addiitional customized information to view. Also, view technology needs to be considered, and should not
 * get hard coded anywhere in application.</p>
 * <p>Using this approache, we can 
 * <p>1. Send additional data to view</p>
 * <p>2. Decide which viw to be rendered at runtime.</p>
 * <p>3. Decouple, hard coded error pages and view technology from web.xml</p>
 * </p>
 * @author amit
 *
 */
// NOTE: all other exception handler has more preference over this.
@Controller
@RequestMapping(value = "/errors")
public class ErrorHandlingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandlingController.class);

	/**
	 * NOTE, this HttpServletReuest object is same object at which error occured, so it contains all attribute we sent and information like url etc.
	 * Servlet spec 3.0 sends same HttpServletReuest and HttpServletResponse objects here, if <location> in <error-page> refers to jsp/servlet.
	 * @param httpServletRequest
	 * @return
	 */
	@GetMapping
	public ModelAndView renderErrorPage(HttpServletRequest httpServletRequest)  {
		ModelAndView modelAndView = new ModelAndView();
		Integer httpErroStatus = getHttpResponseCode(httpServletRequest);
		String message = (String) httpServletRequest.getAttribute("javax.servlet.error.message"); // custom attribute servlet contaier setnds. refer https://www.logicbig.com/tutorials/java-ee-tutorial/java-servlet/error-handling.html
		
		LOGGER.info("Inside ErrorHandlingController ... errorCode :: {}, message :: {}", httpErroStatus, message);
		modelAndView.addObject("errorMessage", message);
		if (httpErroStatus.equals(404)) {
			modelAndView.setViewName("errors/notFound");
		} else if (httpErroStatus.equals(403)) {
			modelAndView.setViewName("errors/forbidden");
		} else {
			modelAndView.setViewName("errors/internalServerError");
		}
		return modelAndView;
	}
	
	/**
	 * Servlet 3.0 spec puts some, custom attributes into request before forwarding request to servlet. One of them is status_code, which we can use to identify error occured.
	 * refer for all attributes servlet 3.0 sends https://www.logicbig.com/tutorials/java-ee-tutorial/java-servlet/error-handling.html
	 * @param httpServletRequest
	 * @return
	 */
	private Integer getHttpResponseCode(HttpServletRequest httpServletRequest) {
		return (Integer) httpServletRequest.getAttribute("javax.servlet.error.status_code");
	}
	
	
}
