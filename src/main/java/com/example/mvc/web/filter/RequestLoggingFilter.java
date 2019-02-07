package com.example.mvc.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestLoggingFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);
	
	private FilterConfig filterConfig;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		logger.info("RequestLoggingFilter initialized");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		logger.info("Request received for, uri :: " + httpServletRequest.getRequestURI() + " method :: " + httpServletRequest.getMethod());
		chain.doFilter(request, response);
		logger.info("Returning request.");
	}

	@Override
	public void destroy() {
		// nothing to do here now.
		logger.info("RequestLoggingFilter detroyed");
	}
}
