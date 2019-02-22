/**
 * 
 */
package com.example.mvc.configuration.security;

import javax.servlet.ServletContext;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.multipart.support.MultipartFilter;

/**
 * This class configures DelegatingFiterProxy filter in servlet context and
 * springSecurityFilterChain bean in application context
 * 
 * @author amit
 *
 */
public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {

	/**
	 * This ensures that the MultipartFilter is specified before the Spring Security
	 * filter. Specifying the MultipartFilter before the Spring Security filter
	 * means that there is no authorization for invoking the MultipartFilter which
	 * means anyone can place temporary files on your server. However, only
	 * authorized users will be able to submit a File that is processed by your
	 * application. In general, this is the recommended approach because the
	 * temporary file upload should have a negligble impact on most servers.
	 * 
	 * SOUFCE: https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#csrf-multipart
	 * 
	 */
	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		insertFilters(servletContext, new MultipartFilter());
	}
}
