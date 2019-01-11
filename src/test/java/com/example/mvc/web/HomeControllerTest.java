/**
 * 
 */
package com.example.mvc.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

/**
 * @author amit 
 * This is first, spring mvc web test case to test controller.
 */

public class HomeControllerTest extends BaseTest {

	/**
	 * Test method for {@link com.example.mvc.web.HomeController#home()}.
	 * @throws Exception 
	 */
	@Test
	public void testHome() throws Exception {
		// print() - Performs operation on result of request execution. We can log/print result after request completion
		// andExpect(handler().handlerType(HomeController.class)) - Used to assert particular controller called after hitting '/XX' uri
		// andExpect(handler().methodName("home")) - Used to assert particular controller's particular method called after hitting '/XX' uri
		mockMvc.perform(get("/")).andDo(print()).andExpect(handler().handlerType(HomeController.class)).andExpect(handler().methodName("home")).andExpect(view().name("home"));
	}
	
	/**
	 * Test method for {@link com.example.mvc.web.HomeController#home()}.
	 * Test with /home url mapping
	 * @throws Exception 
	 */
	@Test
	public void testHome1() throws Exception {
		mockMvc.perform(get("/home")).andDo(print()).andExpect(view().name("home"));
	}
}
