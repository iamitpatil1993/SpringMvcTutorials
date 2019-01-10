/**
 * 
 */
package com.example.mvc.web;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.servlet.ServletContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.mvc.configuration.RootJavaConfig;
import com.example.mvc.configuration.WebConfig;

/**
 * @author amit 
 * Defines base test for our spittle web application
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootJavaConfig.class, WebConfig.class }) // We need to specify both app config as well as web config classes.
@WebAppConfiguration // This is used to declare that the ApplicationContext loaded for an integration test should be a WebApplicationContext.
public class BaseTest {

	@Autowired
	protected WebApplicationContext webApplicationContext;

	protected MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	/**
	 * This test, basic integration test setup and web application context loading is correct and is in place.
	 */
	@Test
	public void testSetup() {
		assertNotNull(mockMvc);

		ServletContext servletContext = webApplicationContext.getServletContext();

		assertNotNull(servletContext);
		assertTrue(servletContext instanceof MockServletContext);
		assertNotNull(webApplicationContext.getBean(HomeController.class));
	}
}
