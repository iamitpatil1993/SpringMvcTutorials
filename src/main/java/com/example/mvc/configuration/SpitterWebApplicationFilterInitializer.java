package com.example.mvc.configuration;

import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;

import com.example.mvc.web.filter.AuthFilter;
import com.example.mvc.web.filter.RequestLoggingFilter;

@Order(2)
public class SpitterWebApplicationFilterInitializer implements WebApplicationInitializer {

	private static final Logger logger = LoggerFactory.getLogger(SpitterWebApplicationFilterInitializer.class);
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		logger.info("initializing additional components for web application");
		
		// Sequence in which below filters will be registered to servlet context, in same order they will get invoked by filterchain.
		// 1. Logging filter will get called first
		Dynamic filterRegistation = servletContext.addFilter("RequestLoggingFilter", RequestLoggingFilter.class);
		filterRegistation.addMappingForUrlPatterns(null, false, "/*");
		
		// 2. Auth filter will get called second, and actual controller after this filter
		Dynamic authFilterRegistration = servletContext.addFilter("AuthFilter", AuthFilter.class);
		authFilterRegistration.addMappingForUrlPatterns(null, false, "/spittles/*");
	}

}
