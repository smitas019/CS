/**
 * 
 */
package com.app.cs.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Komal
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = TestBeanConfig.class)
public class HomeControllerTest {

	/*
	 * @InjectMocks private HomeController homeController;
	 */
	@Autowired
    private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	/**
	 * @throws java.lang.Exception
	 */
	
	@Before
    public void setup() {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();
    }

	@Test
	public void testHomePage() throws Exception {
		this.mockMvc.perform(get("/"))
				.andExpect(status().isOk())/* .andExpect(forwardedUrl("WEB-INF/views/home.jsp")) */;
	}

	@Test
	public void verifyUserTest() throws Exception {
		this.mockMvc.perform(post("/verifyUser")
				.param("userName", "SmitaS").param("password", "Smita123"))
				.andExpect(status().isOk());
				//.andExpect(redirectedUrl("/index.htm"));/* .andExpect(forwardedUrl("WEB-INF/views/home.jsp")) */;
	}
	
	@Test
	public void createPostTest() throws Exception {
		this.mockMvc.perform(post("/createPost")
				.param("content", "Dummy Content"))
				.andExpect(status().isOk());
				//.andExpect(redirectedUrl("/index.htm"));/* .andExpect(forwardedUrl("WEB-INF/views/home.jsp")) */;
	}
	
	
	
	@Test
	public void testLogOut() throws Exception {
		this.mockMvc.perform(get("/logout"))
				.andExpect(status().isOk())/* .andExpect(forwardedUrl("WEB-INF/views/home.jsp")) */;
	}
}
