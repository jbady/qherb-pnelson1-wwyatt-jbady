package edu.ycp.cs320.entrelink.userdb.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.entrelink.model.Post;
import edu.ycp.cs320.entrelink.model.User;

// initializes fake database with users
public class InitialData {
	public static List<User> getUsers() throws IOException {
		List<User> userList = new ArrayList<User>();
		
		ReadCSV readUsers = new ReadCSV("usersTable.csv");
		try {
			Integer userID = 1;
			while(true) {
				List<String> tuple = readUsers.next();
				if(tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				User newUser = new User();
				newUser.setUserId(userID++);
				newUser.setUsername(i.next());
				newUser.setUserFirstName(i.next());
				newUser.setUserLastName(i.next());
				newUser.setEmail(i.next());
				newUser.setPassword(i.next());
				newUser.setUserType(i.next());
				newUser.setProfilePic(i.next());
				newUser.setWebsite(i.next());
				newUser.setBio(i.next());
				newUser.setMajor(i.next());
				newUser.setStatus(i.next());
				newUser.setInterests(i.next());
				newUser.setSkills(i.next());
				userList.add(newUser);
			}
			return userList;
		}finally {
			readUsers.close();
		}
	}
	
	public static List<Post> getPosts() throws IOException {
		List<Post> postList = new ArrayList<Post>();
		
		ReadCSV readPosts = new ReadCSV("postsTable.csv");
		try {
			Integer postID = 1;
			while(true) {
				List<String> tuple = readPosts.next();
				if(tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Post newPost = new Post();
				newPost.setPostId(postID++);
				newPost.setPosterId(Integer.parseInt(i.next()));
				newPost.setName(i.next(), "");
				newPost.setTimePosted(i.next());
				newPost.setTitle(i.next());
				newPost.setDescription(i.next());
				postList.add(newPost);
			}
			return postList;
		}finally {
			readPosts.close();
		}
	}
}