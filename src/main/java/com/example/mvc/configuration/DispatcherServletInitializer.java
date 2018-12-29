package com.example.mvc.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 
 * @author amit
 * This class is Counterpart of DispatcherServlet declaration and configuration in web.xml.
 * It is used to confiure dispatcher servlet in programmatic manner.
 *
 */

public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * This defines javaConfig class for middler-tier beans which are shared by all web components.
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { RootJavaConfig.class };
	}

	/**
	 * This defines beans and components for spring mvc web part.
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebConfig.class };
	}

	/**
	 * This defines that, this dispatcher servlet will handle all request to application and will act as a front controller for application.
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
