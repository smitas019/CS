/**
 * 
 */
package co.app.cs.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.app.cs.entity.Post;
import com.app.cs.entity.User;

/**
 * @author Komal
 *
 */
public class InitialiseData {

	private static  Set<User> users = new HashSet<User>();
	private static  SimpleDateFormat sdformat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
	private static List<Post> newsFeedsList = new ArrayList<Post>();
	static {
		try {
			
			//List<String> followersList = new ArrayList<String>();
			/*
			 * followersList.add("ShwetaS"); followersList.add("Karan");
			 * followersList.add("Suhas");
			 */
			User user = new User(1, "SmitaS", "Smita123", "smitas019@gmail.com",
					"7709603456", sdformat.parse("19-04-1990 06:20:20"), "Smita", "Satpute");
			//user.setFollowers(followersList);
			
			
			/*
			 * List<String> followersList_1 = new ArrayList<String>();
			 * followersList_1.add("SmitaS"); followersList_1.add("Suhas");
			 */
			User user_1 = new User(2, "ShwetaS", "Shweta123", "shweta@gmail.com",
					"7709603452", sdformat.parse("02-04-1992 13:20:20"), "Shweta", "Satpute");
			//user_1.setFollowers(followersList_1);
			
			User user_2 = new User(3, "Karan", "Karan123", "karan@gmail.com",
					"1234567899", sdformat.parse("02-11-1986 13:20:20"), "Karan", "Satpute");
			
			User user_3 = new User(4, "Suhas", "Suhas123", "suhas@gmail.com",
					"876543219", sdformat.parse("11-11-1989 13:20:20"), "Suhas", "Gaikwad");
			
			users.add(user);
			users.add(user_1);
			users.add(user_2);
			users.add(user_3);
			
			setNewsFeedsList();
			updateNewsFeed();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void updateNewsFeed() {
		
		users.forEach(usr->{
			List<Post> userPostList=newsFeedsList.stream().sorted().limit(20).filter(
					post-> usr.getFollowers().contains(post.getPostedBy())
					|| usr.getUserName().equals(post.getPostedBy())
					).map(post->post).collect(Collectors.toList());
			
			usr.setUsersPost(userPostList);
			
		});
	}
	
	/**
	 * @param userName
	 * @return
	 */
	public static User getUser(String userName) {
		return users.stream().filter(usr -> usr.getUserName().equals(userName)).findAny()
				.orElse(null);
	}

	/**
	 * @return the users
	 */
	public static Set<User> getUsers() {
		return users;
	}

	/**
	 * @return the sdformat
	 */
	public  SimpleDateFormat getSdformat() {
		return sdformat;
	}
	
	/**
	 * @throws ParseException
	 */
	private static  void setNewsFeedsList() throws ParseException{
		
		Post post=new Post(101, "News Feed 1", "SmitaS", new Date(System.currentTimeMillis()));
		Post post_1=new Post(102, "News Feed 2", "SmitaS", sdformat.parse("09-12-2019 13:20:20"));
		
		Post post_2=new Post(103, "News Feed 3", "ShwetaS", sdformat.parse("02-12-2019 11:20:20"));
		Post post_3=new Post(104, "News Feed 4", "ShwetaS", new Date(System.currentTimeMillis()));
		
		Post post_4=new Post(105, "News Feed 5", "SmitaS", new Date(System.currentTimeMillis()));
		Post post_5=new Post(106, "News Feed 6", "SmitaS", sdformat.parse("22-11-2019 15:20:20"));
		
		Post post_6=new Post(107, "News Feed 7", "Suhas", new Date(System.currentTimeMillis()));
		Post post_7=new Post(108, "News Feed 8", "Karan", new Date(System.currentTimeMillis()));
		
		Post post_8=new Post(109, "News Feed 9", "Suhas", sdformat.parse("12-11-2019 13:20:20"));
		Post post_9=new Post(110, "News Feed 10", "Karan", new Date(System.currentTimeMillis()));
		
		Post post_10=new Post(111, "News Feed 11", "Suhas", sdformat.parse("01-12-2019 09:20:20"));
		Post post_11=new Post(112, "News Feed 12", "Karan", new Date(System.currentTimeMillis()));
		
		Post post_12=new Post(113, "News Feed 13", "ShwetaS", sdformat.parse("08-12-2019 11:20:20"));
		Post post_13=new Post(114, "News Feed 14", "ShwetaS", sdformat.parse("08-12-2019 10:20:20"));
		
		Post post_14=new Post(115, "News Feed 15", "Suhas", sdformat.parse("07-12-2019 18:20:20"));
		Post post_15=new Post(116, "News Feed 16", "Suhas", new Date(System.currentTimeMillis()));
		
		Post post_16=new Post(117, "News Feed 17", "Karan", sdformat.parse("06-12-2019 16:20:20"));
		Post post_17=new Post(118, "News Feed 18", "Karan", sdformat.parse("05-12-2019 13:20:20"));
		
		Post post_18=new Post(119, "News Feed 19", "SmitaS", sdformat.parse("04-12-2019 19:20:20"));
		Post post_19=new Post(120, "News Feed 20", "SmitaS", new Date(System.currentTimeMillis()));
		
		Post post_20=new Post(121, "News Feed 21", "ShwetaS", new Date(System.currentTimeMillis()));
		
		
		newsFeedsList.add(post);
		newsFeedsList.add(post_1);
		newsFeedsList.add(post_2);
		newsFeedsList.add(post_3);
		newsFeedsList.add(post_4);
		newsFeedsList.add(post_5);
		newsFeedsList.add(post_6);
		newsFeedsList.add(post_7);
		newsFeedsList.add(post_8);
		newsFeedsList.add(post_9);
		newsFeedsList.add(post_10);
		newsFeedsList.add(post_11);
		newsFeedsList.add(post_12);
		newsFeedsList.add(post_13);
		newsFeedsList.add(post_14);
		newsFeedsList.add(post_15);
		newsFeedsList.add(post_16);
		newsFeedsList.add(post_17);
		newsFeedsList.add(post_18);
		newsFeedsList.add(post_19);
		newsFeedsList.add(post_20);
		
	}

	/**
	 * @return the newsFeedsList
	 */
	public static  List<Post> getNewsFeedsList() {
		return newsFeedsList;
	}
	
}
