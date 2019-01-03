/**
 * 
 */
package com.example.mvc.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;

/**
 * @author amit
 * This is another implementation of WebApplicationInitializer, difference is this one is direct implementation and AbstractAnnotationConfigDisptacherServletInitializer will always create and register dispatcher servlet to servlet context.
 * Since, AbstractAnnotationConfigDisptacherServletInitializer will always create and register dispatcher servlet to servlet context, we can not and should not have multiple classes in classpath extending it, because it will register duplicate DispatcherServlet to container at same name and mappings, whose behavior is unknown 
 *
 */

@Order(1) // Since, it has order greater than another one, it will get called after SpitterWebAppInitializer
public class SpitterWebAppInitializer2 implements WebApplicationInitializer {
	
	private static final Logger logger = LoggerFactory.getLogger(SpitterWebAppInitializer2.class);

	// If you check the implementation of AbstractAnnotationConfigDisptacherServletInitializer, it has same implementation
	// where it programmatically registers DispatcherServlet to servletContainer using javax.servlet apis.
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		logger.info("Inside SpitterWebAppInitializer2.onStartup() callback handler ...");
	}

	
}
