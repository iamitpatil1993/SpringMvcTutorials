package com.example.mvc.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * This class is to demostrate, HandlerIntercepror in springs. This interceptor
 * calculates and logs time taken by request to complete.
 * 
 * @author amit
 *
 */

// NOTE: Siince this is spring bean in application context, it is singleton, so never put any instance variables in this class. Treat them as Servlet or Filters which are always singleton in java ee.
public class RequestTimeLoggingInterceptor implements HandlerInterceptor {

	// Should never have instacne variables.
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestTimeLoggingInterceptor.class);
	private static final String ATTRIBUTE_KEY_STRT_TIME = "startTime";

	/**
	 * This gets called beofore controller get called.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		LOGGER.info("Pre-handler: Calculating time for request with URL :: {} and http method :: {}", request.getRequestURI(), request.getMethod());
		request.setAttribute(ATTRIBUTE_KEY_STRT_TIME, System.currentTimeMillis());
		return true;
	}

	/**
	 * This gets called after controller execution but before view rendered.
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		LOGGER.info("Ppost-handler: Calculating time for request with URL :: {} and http method :: {}", request.getRequestURL(), request.getMethod());
	}

	/**
	 * This get called after view get rendered or error occured while processing request.
	 * This get executed only if
	 *  1. preHandler returned true.
	 *  2. preHandler returned successfully without any exception
	 * Does not get called on redirection.
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)  throws Exception {
		LOGGER.info("after-completion-handler: Calculating time for request with URL :: {} and http method :: {}", request.getRequestURL(), request.getMethod());	
		Long startTime = (Long) request.getAttribute(ATTRIBUTE_KEY_STRT_TIME);
		LOGGER.info("time take by request with URL :: {} and http method :: {}, is :: {} milisseconds", new Object[] {request.getRequestURI(), request.getMethod(), Long.valueOf(System.currentTimeMillis() - startTime)});
	}
}
