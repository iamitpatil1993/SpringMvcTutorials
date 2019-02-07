/**
 * 
 */
package com.example.mvc.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * This is another interceptor added to check how ordering takes place, if i have multiple interceptors registered on handler.
 * @author amit
 *
 */
public class RequestLoggingInterceptor implements HandlerInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequestLoggingInterceptor.class);

	/**
	 * This gets called before	 controller get called.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		LOGGER.info("Inside Pre-handler");
		return true;
	}

	/**
	 * This gets called after controller execution but before view rendered.
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		LOGGER.info("Inside Post-handler");
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
		LOGGER.info("Inside after-completion-handler");	
	}
}
