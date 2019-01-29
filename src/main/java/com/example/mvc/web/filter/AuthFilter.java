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

public class AuthFilter implements Filter {

private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);
	
	private FilterConfig filterConfig;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		logger.info("AuthFilter initialized");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		logger.info("Authenticating Request for, uri :: " + httpServletRequest.getRequestURI() + " method :: " + httpServletRequest.getMethod());
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		logger.info("AuthFilter destroyed");
	}

}
