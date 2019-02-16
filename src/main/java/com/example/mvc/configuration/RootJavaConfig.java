/**
 * 
 */
package com.example.mvc.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author amit
 * This is application java config class to define beans for middle and backend of web application.
 * This config class is used by ContextLoaderListener to create one of two application contexts in spring mvc web application.
 *
 */

@Configuration
@ComponentScan(basePackages = {"com.example.mvc"}, excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = EnableWebMvc.class))
public class RootJavaConfig {
	// middle-tier bean configuration will be here
}
	