/**
 * 
 */
package com.example.mvc.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.mvc.configuration.RootJavaConfig;
import com.example.mvc.configuration.WebConfig;

/**
 * @author amit 
 * This is first, spring mvc web test case to test controller.
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RootJavaConfig.class, WebConfig.class}) // We need to specify both app config as well as web config classes.
@WebAppConfiguration // This is used to declare that the ApplicationContext loaded for an integration
					 // test should be a WebApplicationContext.
public class HomeControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

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
	
	/**
	 * This test shows/verifies that, out WebApplicationContext is initialized correctly and we are using mocked servletContext.
	 */
	@Test
	public void givenWac_whenServletContext_thenItProvidesGreetController() {
	    ServletContext servletContext = wac.getServletContext();
	     
	    Assert.assertNotNull(servletContext);
	    Assert.assertTrue(servletContext instanceof MockServletContext);
	    Assert.assertNotNull(wac.getBean("homeController")); // ID of home controller will be same as one for @Component classes i.e class name with first character in small case.
	}

}
