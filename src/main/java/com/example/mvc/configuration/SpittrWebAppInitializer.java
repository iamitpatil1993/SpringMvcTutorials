package com.example.mvc.configuration;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.example.mvc.web.filter.DispatcherServletFilter;

/**
 * 
 * @author amit 
 * This class is Counterpart of DispatcherServlet declaration and
 *         configuration in web.xml. It is used to confiure dispatcher servlet
 *         in programmatic manner.
 *
 */

@Order(0) // We can have multiple implementations of WebApplicationInitializer interface
			// in classpath. SpringCServletContainer will find all implementations of
			// WebApplicationInitializer in classpath.
			// And then it will, call onStartup(ServletContext) method of each. If there is
			// no single implementation found in classpath, not error will be thrown simply
			// info level logger will be printed on console.
			// If there are multiple implementations of interface then, it will call
			// onstartup() method on each in random order unless implementation classes
			// defines the order usiing @Ordered annotation or implement Order interface.
			// In our case, there are two implementations on is direct
			// SpittrWebAppInitializer2 and another one SpittrWebAppInitializer is indirect
			// via AbstractAnnotationConfigDispatcherServletInitializer.
			// So, both of these initializers will get called at server startup by Spring
			// SPI for container initializer. We can have as many as implementations in
			// classpath (but we should avoid and should have only one.)
// SInce this initializer has given order of 0 which means highest priority, so it will always get invoked first.
public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpittrWebAppInitializer.class);

	public SpittrWebAppInitializer() {
	}

	/**
	 * This defines javaConfig class for middler-tier beans which are shared by all
	 * web components.
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
		LOGGER.info("Inside SpittrWebAppInitializer ...");
		return new Class[] { WebConfig.class };
	}

	/**
	 * This defines that, this dispatcher servlet will handle all request to
	 * application and will act as a front controller for application.
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	/**
	 * This method can be overridden to customize DispatcherServlet configurations.
	 * Super classes has empty implementation of this method, and it calls this method after disptacher servlet registered to container.
	 * So, this method is like hook to customize registered dispatcher servlet to servlet container.
	 */
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement("/home/amit/")); // Adding multi-part support for registered DispatcherServlet
		registration.setLoadOnStartup(1); // Making it to load on start up
		registration.setInitParameter("dummyInitParam", "dummyValue"); // Adding init-param for DispatcherServlet.
		// we can use other methods of Dynamic to customize registered DispatcherSrevlet to srevlet container.
		
		// we need to declare temporary location where file should be kept during multipart file conversion. Without this it will not work
		registration.setMultipartConfig(new MultipartConfigElement("/home/amit/spittleFiles"));
	}
	
	/**
	 * Override this method and return array of filter that needs to be applied on DispatcherServlet only.
	 * These filters will be registered to Servlet context and will be invoked on any request matching dispatcher servlet url pattern
	 *
	 *NOTE: Order in which we declare filters here, will be same order in which filters wlll be called. So, order of filter in array should be as per expected and should not be random
	 */
	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] { new DispatcherServletFilter() };
	}
}
