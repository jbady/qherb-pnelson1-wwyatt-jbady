package edu.ycp.cs320.entrelink.userdb.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.ycp.cs320.entrelink.model.Post;
import edu.ycp.cs320.entrelink.model.User;

public class FakeDatabase implements IDatabase {
	
	private List<User> userList;
	private List<Post> postList;
	
	public FakeDatabase() {
		userList = new ArrayList<User>();
		postList = new ArrayList<Post>();
		
		// Add initial data
		readInitialData();
		
		System.out.println(userList.size() + " users in database");
	}

	public void readInitialData() {
		try {
			userList.addAll(InitialData.getUsers());
			postList.addAll(InitialData.getPosts());
			
		} catch (IOException e) {
			throw new IllegalStateException("Couldn't read initial data", e);
		}
	}
	
	// finds users through email or username
	@Override
	public User findUserByEmailOrUsername(String username) {
		User result = new User();
		for (User user : userList) {
			if((user.getUsername().equals(username)) || user.getEmail().equals(username)){
				result = user;
			}
		}
		return result;
	}
	
	public ArrayList<Post> findPostsByTags(ArrayList<String> tags){
		
		ArrayList<Post> postsContainingTags = new ArrayList<Post>();
		
		for(int i = 0; i < postList.size(); i++) {
			ArrayList<String> tagsInPosts = postList.get(i).getTags();
			for(int j = 0; j < tagsInPosts.size(); j++) {
				for(int k = 0; k < tags.size(); k++) {
					if(tagsInPosts.get(j).toLowerCase().equals(tags.get(k).toLowerCase())) {
						postsContainingTags.add(postList.get(i));
					}
				}
			}
		}
		
		return postsContainingTags;
		
	}
	
}
