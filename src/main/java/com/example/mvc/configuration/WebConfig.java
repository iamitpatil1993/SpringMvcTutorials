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
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

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
public class WebConfig extends WebMvcConfigurerAdapter {
	
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

}
