package com.example.mvc.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		logger.info("Inside home controller...");
		return "home";
	}

}
