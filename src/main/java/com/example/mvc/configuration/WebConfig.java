/**
 * 
 */
package com.example.mvc.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

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
		viewResolver.setOrder(10);  // In order to use tilesViewResolver over internalResorceViewResolver, we either
									// need to remove this bean declaration or define it to be at low order. So, we
									// are using setOrder to define this viewResolver to be at low order as compare
									// to TilesViewResover declared below in this file.
									// This is also called as ViewResolver chaining in spring.
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
		registry.addResourceHandler("/files/**").addResourceLocations("file:/home/amit/"); // this is how we can provide mapping to resources on disk. We need to use 'file:' as a prefix and it spring will consider looking resource in file system.
	}
	
	/**
	 * We need to provide message source for resource bundle. We can use two classes for it ResourceBundleMessageSource or ReloadableResourceBundleMessageSource
	 * This MessageSource uses JDKs default ResourceBundle locator implementation so we nned to follow conventions as mentioned in ResourceBundle java class docs.
	 * This message source can  only look for resource bundle in class path and can't look in file system.
	 * This message source can not reload resource bundle. Once read, it caches them forever and hence resources bundle can't be updated/reloaded at runtime and needs JVM restart.
	 * @return
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource bundleMessageSource = new ResourceBundleMessageSource();
		bundleMessageSource.setBasenames("messagesource.spittle", "messagesource.ValidationMessagesSpittle", "messagesource.webmessage.home"); 
		bundleMessageSource.setDefaultEncoding("UTF-8");
		return bundleMessageSource;
	}
	
	/**
	 * This is example of using ReloadableResourceBundleMessageSource to configure message source. Why use ReloadableResourceBundleMessageSource over ResourceBundleMessageSource, check online.
	 * This message source can look for resource bundles in classpath as well as in file system.
	 * It can reload resource bundles periodically, so we can use this messageSource implementation to update resource bundle at runtime without JVM restart.
	 * Since, this resource bundle does not uses JDKs default resource locator implementation and uses it's implementation we can provide paths for resource bundles in spring specific way like prefix with classpath: or file: to refer resource bundle locations in classpath and/or file system.	
	 */
	/*@Bean
	public MessageSource messageaSource() {
		ReloadableResourceBundleMessageSource bundleMessageSource = new ReloadableResourceBundleMessageSource();
		bundleMessageSource.setBasenames("classpath:messagesource/spittle", "classpath:messagesource/ValidationMessagesSpittle");
		bundleMessageSource.setDefaultEncoding("UTF-8");
		return bundleMessageSource;
	}*/
	
	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	    bean.setValidationMessageSource(messageSource());
	    return bean;
	}
	
	/**
	 * We need to define this bean in order to configure Apache tiles container and definition files
	 * @return
	 */
	@Bean(destroyMethod = "destroy") // Call destroy method to close TilesContainer before this bean get destroyed.
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions("/WEB-INF/layouts/defs/tiles.xml"); // Provide list of apache tiles definition config files
		tilesConfigurer.setCheckRefresh(true);
		return tilesConfigurer;
	}
	
	/**
	 * We need to use TilesViewResolver over InternalResourceViewResolver. Configuration is simple, we need to just define this bean.
	 * @return
	 */
	@Bean
	public ViewResolver tilesViewResolver() {
		TilesViewResolver tilesViewResolver = new TilesViewResolver();
		tilesViewResolver.setOrder(0); // this is how we can do viewResolver chaining. Spring DispatcheServlet checks
										// which viewResolver returns view from all declared viewResolvers in spring
										// context. Using this setOrder() method we can chain view resolvers and define
										// view resolver order.
		return tilesViewResolver;
	}
	
	/**
	 * This is called as view controllers. Here we define static binding between url and logical view name of view to be rendered for that url.
	 * This is mostly used when, there are uris or view in which case we do not have any logic in controller method other than just returning logical view name.
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/homePage").setViewName("home"); // This says, when url is /homePage render view with logical view name "home". NOTE: It uses configured ViewResolver for determining actual view instance and implementation for same.
	}
}
