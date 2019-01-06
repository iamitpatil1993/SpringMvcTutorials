package com.example.mvc.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.mvc.configuration.RootJavaConfig;
import com.example.mvc.configuration.WebConfig;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { RootJavaConfig.class, WebConfig.class })
@WebAppConfiguration // for standalone mode as well, we need to add this, in order to add spring
						// spring web mvc behavior to test/ is used to declare that the
						// ApplicationContext loaded for an integration test should be a
						// WebApplicationContext.
public class HomeControllerUsingStandaloneMockMvcTest {

	@InjectMocks
	private HomeController homeController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		// Process mock annotations
		MockitoAnnotations.initMocks(this);

		// Setup Spring test in standalone mode,
		// Standalone mode is to, create mockMvc object with minimal configuration to
		// test specific controllers only.
		this.mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
	}

	@Test
	public void test() throws Exception {
		mockMvc.perform(get("/")).andExpect(view().name("home"));
	}

}
