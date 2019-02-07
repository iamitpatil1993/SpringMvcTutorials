package com.example.mvc.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a simple servlet filter, which we only want to apply to all request goes to DispatcherSrevlet. I.e we want this filte to be
 * applied for request belonging to DispatcherServlet only. (Whatever may be url mapping for dispatcher servlet.)
 * 
 * We will decalre this filter to be applied only to disptacher servlet in one of WebApplicationinitializer classes by overriding getServletFilters() method

 * @author amit
 *
 */
public class DispatcherServletFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(DispatcherServletFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// Nothing to do here 
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LOGGER.info("Request received for dispacher servlet.");
		chain.doFilter(request, response);
		LOGGER.info("Returning request received for dispacher servlet.");
	}

	@Override
	public void destroy() {
		// Nothing to do here
	}
}
