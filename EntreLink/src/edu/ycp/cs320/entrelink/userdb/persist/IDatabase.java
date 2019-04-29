package edu.ycp.cs320.entrelink.userdb.persist;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.entrelink.model.Post;
import edu.ycp.cs320.entrelink.model.User;

public interface IDatabase {
	public User findUserByEmailOrUsername(String username);
	public User insertNewUser(String username, String password, String userFirstName, String userLastName, String email,
			String userType,	String bio, String major, String status, String interest, String skills);
	public ArrayList<Post> findPostsByTags(ArrayList<String> tags);
	public void addPostToPostList(Post post);
	public void addMultiplePostsToPostList(ArrayList<Post> posts);
	public ArrayList<Post> findPostsByTitle(String title);
	public ArrayList<Post> findPostsByUserName(String name);
	public ArrayList<Post> findAllPosts();
}
