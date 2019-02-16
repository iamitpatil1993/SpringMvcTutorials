package com.example.mvc.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.example.mvc.exception.SpittleNotFoundException;

/**
 * <p>This class is to demonstrate global exception handling in spring mvc.</p>
 * <p>This class is declared under *.web package, so it will be detected by dispatcher servlet and will be part of web application context created by dispatcher servlet</p>
 * <br/> It make more sese to declare these classes as a part of web app context and not part of root context because, we do not want these classes to be able to get injected into root context beans.
 * <br/>
 * This class can handle exceptions thrown from <br/>
 *</t> 1. All controllers in application. <br/>
 *</t> 2. All interceptors in applications.
 * 
 * @author amit
 *
 */
@ControllerAdvice // We can us attributes of this annotation to limit which controllers this exception handler advise should apply/advise. If we we do not specify, it will be applied to all controllers in web application context.
public class GlobalExceptionHandler {

	@ResponseStatus(code = HttpStatus.NOT_FOUND) // As I said, this annotation is general purpose and can be used on controller methods to map response status code.
	@ExceptionHandler(SpittleNotFoundException.class)
	public ModelAndView handleSpittleNotFoundException(SpittleNotFoundException exception) {
		// all code in this is exactly similar to request handling code
		ModelAndView modelAndView = new ModelAndView("errors/notFound");
		modelAndView.addObject("errorMessage", exception.getMessage());
		return modelAndView;
	}
	
	//@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR) // As I said, this annotation is general purpose and can be used on controller methods to map response status code.
	//@ExceptionHandler(Throwable.class)
	public String handleGenericException(Throwable exception) {
		return "errors/internalServerError";
	}
}
