package com.quick.start;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.quick.start.controllers.HomeController;

@SpringBootTest
public class HomeControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private HomeController homeController;
	
	 @BeforeEach
	public void setup() {
		mockMvc=MockMvcBuilders.standaloneSetup(homeController).build();
	}
	
	@Test
	public void testHome() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().is(302));
	}

}
