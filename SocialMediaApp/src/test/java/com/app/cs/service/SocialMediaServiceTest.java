/**
 * 
 */
package com.app.cs.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.app.cs.entity.User;

import co.app.cs.data.InitialiseData;

/**
 * @author Komal
 *
 */
public class SocialMediaServiceTest {

	@Mock
    private DataStore dataStore;
	
	
	@InjectMocks
	SocialMediaService socialMediaService;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testForValidUser() {
		// enter data present in memory
		User validResp=validateUserTest("SmitaS", "Smita123");
		assertNotNull(validResp);
		
		// enter invalid data not present in memory
		User invalidResp=validateUserTest("ABC", "XYZ");
		assertNull(invalidResp);
		
	}
	
	/**
	 * @param userName
	 * @param password
	 * @return
	 */
	private User validateUserTest(String userName,String password) {
		when(dataStore.getUsers()).thenReturn(InitialiseData.getUsers());
		User resp=socialMediaService.validateUser(userName,password);
		return resp;
	}
	
	@Test
	public void testForCreatePost() {
		when(dataStore.getNewsFeedsList()).thenReturn(InitialiseData.getNewsFeedsList());
		when(dataStore.updateNewsFeed()).thenReturn(true);
		assertTrue(socialMediaService.createPost("SmitaS", 1, "content"));
	}
	
	@Test
	public void testForFollowUser() {
		
		when(dataStore.getUser("SmitaS")).thenReturn(InitialiseData.getUser("SmitaS"));
		when(dataStore.updateNewsFeed()).thenReturn(true);
		// follow user present in db
		assertTrue(socialMediaService.follow("SmitaS", "ShwetaS"));
		// follow user not present in db
		assertFalse(socialMediaService.follow("XDF", "MNJ"));
	}
	
	@Test
	public void testForUnFollowUser() {
		when(dataStore.getUser("SmitaS")).thenReturn(InitialiseData.getUser("SmitaS"));
		when(dataStore.updateNewsFeed()).thenReturn(true);
		// follow user present in db
		assertTrue(socialMediaService.unfollow("SmitaS", "ShwetaS"));
		// follow user not present in db
		assertFalse(socialMediaService.unfollow("XDF", "MNJ"));
	}
	
	@Test
	public void testForSearchUser() {
		when(dataStore.getUsers()).thenReturn(InitialiseData.getUsers());
		when(dataStore.getUser("SmitaS")).thenReturn(InitialiseData.getUser("SmitaS"));
		// follow user present in db
		assertNotNull(socialMediaService.search("Suhas", "SmitaS"));
		// follow user not present in db
		assertNotNull(socialMediaService.search("INVALID", "ShwetaS"));
	}
	
	

}
