/**
 * 
 */
package com.example.mvc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * @author amit
 * This config class defines configuration for spring mvc web components.
 * This class is used by dispatcher servlet to create one of two application context in spring mvc web application.
 * NOTE: Application context created by using this class will be chile application context of another one created by 
 * 	ServletCOntextListner using RootConfig, this is because, beans in this application context can not be shared/used/injected into
 * middle-tier or backend beans, where as apposite is true. Therefore this application context is considered as child and 
 * second one is considered as parent.
 *
 */

@SuppressWarnings("deprecation")
@Configuration
@ComponentScan(basePackages = "com.example.mvc.web") // Specify package containing web components
@EnableWebMvc // Enables spring mvc
public class WebConfig implements WebMvcConfigurer { // WebMvcConfigurerAdapter class is deprecated in spring 5, so we can use WebMvcConfigurer directly.  What WebMvcConfigurerAdapter doing is, providing default (empty) implementation for WebMvcConfigurer interface so that we can override only required methods from WebMvcConfigurer interface. But Java 8, have added support that interface methods can have default implementations, so Adaptrer class is of no use no and we can implement interface directly and override only required methods, since interface provides default implementation for others.    
	
	/**
	 * This is ViewResolver declaration, here we are defining JSP view resolver.
	 * @return
	 */
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setExposeContextBeansAsAttributes(true);
		viewResolver.setViewClass(JstlView.class); // In order to use JSTL in our JSP pages, we need to use JstlView
													// over InternalResourceView. Till not it was working without
													// setting this property because spring automatically checks the
													// class path for JSTL config class exists and can be loaded? If it
													// exists and can be loaded it will automatically set jstlEnabled
													// flag to true and uses JstlView over InternalResourceView.
													// Check InternalResourceViewResolver() constructor implementation in source.
		return viewResolver;
	}
	
	/**
	 * This configuration says that, default servlet of container is enabled and it will handle 
	 * responsibility of serving static content and dispatcher servlet (front controller for spring mvc) will not 
	 * handle anything regarging serving static contents. (Resources and pages)
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/**
	 * This use to map, for what request url to where spring should look for static resources. 
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCachePeriod(5);
		//registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCachePeriod(100) // we can set time in seconds as well, for how much seconds we want to cache these resources. We can also define multiple resource mappings.
	}

}
