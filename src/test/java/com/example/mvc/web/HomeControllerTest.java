/**
 * 
 */
package com.example.mvc.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
		mockMvc.perform(get("/")).andExpect(view().name("home"));
	}
	
	/**
	 * Test method for {@link com.example.mvc.web.HomeController#home()}.
	 * Test with /home url mapping
	 * @throws Exception 
	 */
	@Test
	public void testHome1() throws Exception {
		mockMvc.perform(get("/home")).andExpect(view().name("home"));
	}
}
