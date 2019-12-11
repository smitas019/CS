/**
 * 
 */
package com.app.cs.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.app.cs.entity.User;
import com.app.cs.service.SocialMediaService;

/**
 * @author Komal
 *
 */

public class HomeControllerTest {

	/*
	 * @InjectMocks private HomeController homeController;
	 */
	
	private MockMvc mockMvc;
	
	@Mock
    private SocialMediaService socialMediaService;
	
	@InjectMocks
    private HomeController homeController;
	/**
	 * @throws java.lang.Exception
	 */
	
	@Before
    public void setup() {
        
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(homeController)
                .build();
    }

	@Test
	public void testHomePage() throws Exception {
		this.mockMvc.perform(get("/"))
				.andExpect(status().isOk()).
				andExpect(forwardedUrl("home"));
	}

	@Test
	public void verifyUserTest() throws Exception {
		User resp=new User("SmitaS","Smita123");
		when(socialMediaService.validateUser("SmitaS","Smita123")).thenReturn(resp);
		this.mockMvc.perform(post("/verifyUser")
				.param("userName", "SmitaS").param("password", "Smita123"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("dashboard"));
	}
	
	@Test
	public void createPostTest() throws Exception {
		String content = "content";
		when(socialMediaService.createPost("SmitaS",1,content)).thenReturn(true);
		this.mockMvc.perform(post("/createPost")
				.param(content, "Dummy Content"))
				.andExpect(status().isOk());
				//.andExpect(forwardedUrl("dashboard"));
	}
	
	@Test
	public void searchTest() throws Exception {
		List<User> userList = new ArrayList<User>();
		String name = "Karan";
		when(socialMediaService.search(name,"SmitaS")).thenReturn(userList);
		this.mockMvc.perform(post("/search")
				.param("name", name))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("dashboard"));
	}
	
	@Test
	public void followTest() throws Exception {
		String name = "Suhas";
		when(socialMediaService.follow("SmitaS", name)).thenReturn(true);
		this.mockMvc.perform(post("/search")
				.param("folowee", name))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("dashboard"));
	}
	
	@Test
	public void unfollowTest() throws Exception {
		String name = "Suhas";
		when(socialMediaService.unfollow("SmitaS", name)).thenReturn(true);
		this.mockMvc.perform(post("/search")
				.param("unfolowee", name))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("dashboard"));
	}
	
	@Test
	public void testLogOut() throws Exception {
		this.mockMvc.perform(get("/logout"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("home"));
	}
}
