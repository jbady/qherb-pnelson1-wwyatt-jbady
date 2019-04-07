package edu.ycp.cs320.entrelink.userdb.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.entrelink.model.Post;
import edu.ycp.cs320.entrelink.model.User;

// initializes fake database with users
public class InitialData {
	public static List<User> getUsers() throws IOException {
		List<User> userList = new ArrayList<User>();
		User newUser = new User("pnelson1", "toothbrush", "Patrick", "Nelson", "pnelson1@ycp.edu", "Admin");
		userList.add(newUser);
		newUser = new User("jbady", "fingernail", "Jason", "Bady", "jbady@ycp.edu", "Admin");
		userList.add(newUser);
		newUser = new User("wwyatt", "RockSalt", "William", "Wyatt", "wwyatt@ycp.edu", "Admin");
		userList.add(newUser);
		newUser = new User("qherb", "3legs", "Quintin", "Herb", "qherb@ycp.edu", "Admin");
		userList.add(newUser);
		newUser = new User("djhake", "BDConsultant", "Professor", "Hake", "djhake@ycp.edu", "Business");
		userList.add(newUser);
		newUser = new User("jdoe", "ILikeRocks", "John", "Doe", "jdoe@ycp.edu", "Student");
		userList.add(newUser);
		return userList;
	}
	
	public static List<Post> getPosts() throws IOException {
		List<Post> postList = new ArrayList<Post>();
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("corn");
		tags.add("food");
		tags.add("simulation");
		tags.add("programming");
		tags.add("computer science");
		User user = new User("pnelson1", "toothbrush", "Patrick", "Nelson", "pnelson1@ycp.edu", "Admin");
		Post newPost = new Post(user, 1227, "Corn Simulator", "I think it would be pretty neat if we could simulate the difficult life corn has, because people need to be aware of what they are eating.", tags);
		postList.add(newPost);
		
		tags = new ArrayList<String>();
		tags.add("light");
		tags.add("engineering");
		tags.add("analysis");
		user = new User("qherb", "3legs", "Quintin", "Herb", "qherb@ycp.edu", "Admin");
		newPost = new Post(user, 0155, "Lightbulb Analyzer", "There are times when I wish I could tell if my lights are turned on or not, so I'm looking for someone who can make something to tell me if my lights are turned on or not.", tags);
		postList.add(newPost);
		
		tags = new ArrayList<String>();
		tags.add("design");
		tags.add("arts");
		user = new User("jbady", "fingernail", "Jason", "Bady", "jbady@ycp.edu", "Admin");
		newPost = new Post(user, 0245, "Two-legged Chair", "I feel threatened when a chair has more legs than me, so I'm looking for someone who can make me a chair with only two.", tags);
		postList.add(newPost);
		
		tags = new ArrayList<String>();
		tags.add("food");
		tags.add("arts");
		tags.add("engineering");
		tags.add("3D printing");
		user = new User("wwyatt", "RockSalt", "William", "Wyatt", "wwyatt@ycp.edu", "Admin");
		newPost = new Post(user, 0625, "Cheese Grater Phone Case", "I would like the functionality of a cheese grater, but the portability of a smart phone.", tags);
		postList.add(newPost);
		
		return postList;
	}
}