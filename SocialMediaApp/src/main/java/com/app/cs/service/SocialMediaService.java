/**
 * 
 */
package com.app.cs.service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.cs.entity.Post;
import com.app.cs.entity.User;

/**
 * @author Komal
 *
 */
@Component
public class SocialMediaService {

	/**
	 * @param userName
	 * @param password
	 * @return
	 */
	
	@Autowired
	private DataStore dataStore;
	
	public User validateUser(String userName,String password) {
		User user=new User(userName, password);
		Set<User> usersList=dataStore.getUsers();
		User resp=usersList.stream().filter(usr -> usr.equals(user)).findAny()
		.orElse(null);
		return resp;
	}
	
	/**
	 * @param userName
	 * @return
	 */
	public User getUser(String userName) {
		return dataStore.getUser(userName);
		
	}
	
	/**
	 * @param userId
	 * @param postId
	 * @param content
	 * @return
	 */
	public boolean createPost(String userId,Integer postId,String content) {
		Post post = new Post(postId, content, userId, new Date(System.currentTimeMillis()));
		dataStore.getNewsFeedsList().add(post);
		boolean resp=dataStore.updateNewsFeed();
		return resp;
	}
	
	/**
	 * @param followerId
	 * @param foloweeId
	 * @return
	 */
	public boolean follow(String followerId,String foloweeId) {
		User user=dataStore.getUser(followerId);
		if(user!=null) {
			user.getFollowers().add(foloweeId);
			return dataStore.updateNewsFeed();
		}
		return false;
	}
	
	/**
	 * @param followerId
	 * @param foloweeId
	 * @return
	 */
	public boolean unfollow(String followerId,String foloweeId) {
		User user=dataStore.getUser(followerId);
		if(user!=null) {
			user.getFollowers().remove(foloweeId);
			return dataStore.updateNewsFeed();
		}
		return false;
	}
	/**
	 * @param name
	 * @param loginUser
	 * @return
	 */
	public List<User> search(String name,String loginUser) {
		List<User> userList=dataStore.getUsers().stream().filter(usr -> usr.getFirstName().
				contains(name)).map(post->post).collect(Collectors.toList());
		
		User loginUserObj=dataStore.getUser(loginUser);
		for(User usr: userList) {
			if(loginUserObj.getFollowers().contains(usr.getUserName())) {
					usr.setFollowed("yes");
			}else {
				usr.setFollowed("no");
			}
		}
		return userList;
	}
	
}
