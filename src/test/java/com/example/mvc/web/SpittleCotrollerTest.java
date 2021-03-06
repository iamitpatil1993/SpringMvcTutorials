/**
 * 
 */
package com.example.mvc.web;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.Test;

import com.example.mvc.dto.Spittle;

/**
 * @author amit
 *
 */

public class SpittleCotrollerTest extends BaseTest {

	/**
	 * Test method for {@link com.example.mvc.web.SpittleCotroller#spittles(org.springframework.ui.Model)}.
	 * This test passes, since we have passed default value to query/request parameters
	 * @throws Exception 
	 */
	@Test
	public void testSpittles() throws Exception {
		mockMvc.perform(get("/spittles"))
		.andExpect(view().name("spittles"))
		.andExpect(model().attributeExists("spittleList"))
		.andExpect(model().attribute("spittleList", isA(List.class)))
		.andExpect(model().attribute("spittleList", not(emptyCollectionOf(Spittle.class))))
		.andExpect(model().attribute("spittleList", everyItem(instanceOf(Spittle.class))));
	}

	/**
	 * Version-1 : Passes request parameters via methods - More elegant way
	 * @throws Exception
	 */
	@Test
	public void testSpittlesWithQueryParameters() throws Exception {
		mockMvc.perform(get("/spittles").param("max", "10").param("count", "5")) 
		.andExpect(view().name("spittles"))
		.andExpect(model().attribute("spittleList", not(emptyCollectionOf(Spittle.class))))
		.andExpect(model().attribute("spittleList", hasSize(5)));
	}

	/**
	 * Version-2 : Passes request parameters via uri itself
	 * @throws Exception
	 */
	@Test
	public void testSpittlesWithQueryParameters2() throws Exception {
		mockMvc.perform(get("/spittles").param("max", "10").param("count", "5")) // correct way to pass query/form parameters
		.andExpect(view().name("spittles"))
		.andExpect(model().attribute("spittleList", not(emptyCollectionOf(Spittle.class))))
		.andExpect(model().attribute("spittleList", hasSize(5)));
	}

	/**
	 * Path-Param test
	 * Note the use of hamcrest matchers to assert the Model attribute and their nested properties and their values.
	 * @throws Exception
	 */
	@Test
	public void testSpittle() throws Exception {
		mockMvc.perform(get("/spittles/{spittleId}", "34")) // correct way to pass path parameter.
		.andExpect(view().name("spittle"))
		.andExpect(model().attribute("spittle", notNullValue(Spittle.class)))
		.andExpect(model().attribute("spittle", instanceOf(Spittle.class)))
		.andExpect(model().attribute("spittle", hasProperty("id", is(34L)))); // This is how u can check individual properties and their values as well.
	}
	
	@Test
	public void testShowRegisterPage( ) throws Exception {
		mockMvc.perform(get("/spittles/register")).andExpect(view().name("register"));
	}
	
	@Test
	public void testRegister() throws Exception {
		mockMvc.perform(post("/spittles/register").param("fistName", "Ramesh").param("lastName", "Jadhav").param("username", "rjadhav").param("password", "adsf1234")).andExpect(redirectedUrl("/spittles/profile/rjadhav"));
	}
	
}
