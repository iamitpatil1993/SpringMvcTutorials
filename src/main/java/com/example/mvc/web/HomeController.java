package com.example.mvc.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.mvc.web.filter.AuthFilter;

/**
 * Handles requests for the application home page.
 */
@Controller
//@Component // This does not works, book says, we can use this annoatation in place of
			// @Controller and it will have same effect. @Controller is just stereotype
			// annotation, we coult have replaced it with @Component annotation. But In my
			// case, it did not worked.
			// NOTE: Using @Controller annotation give more information about type of component it is. So, we should always prefer to use annotations that will
			// give more information about typr of component it is for example, @Controller, @Repository etc. over normal @Component
@RequestMapping(value = {"/", "/home"}) // This will be shared by all methods in this controllers.
public class HomeController implements InitializingBean {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	// In ordr to validate our exclude filter in WebConfig class is correct, I tried injecting AuthFilterH here in this controller amd marked it as required false iin order to prevent server startup error.
	@Autowired(required = false)
	private AuthFilter authFilter;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * This will handle GET - /
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String home() {
		logger.info("Inside home controller...");
		return "home";
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// AuthFilter is null here, so out exclude filter in WebConfig class was correct. :)
		if (authFilter == null) {
			logger.info("Our exclude logic is correct");
		} else {
			logger.info("Our exclude is wrong");
		}
	}
}
