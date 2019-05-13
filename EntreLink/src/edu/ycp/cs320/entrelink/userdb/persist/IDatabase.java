package edu.ycp.cs320.entrelink.userdb.persist;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.entrelink.model.Post;
import edu.ycp.cs320.entrelink.model.User;

public interface IDatabase {
	public User findUserByEmailOrUsername(String username);
	public User insertNewUser(String username, String password, String userFirstName, String userLastName, String email,
			String userType);
	public User changeUserBio(String username, String bio);
	public ArrayList<Post> findPostsByTags(ArrayList<String> tags);
	public ArrayList<Post> findPostsByTitle(String title);
	public ArrayList<Post> findPostsByUserName(String name);
	public ArrayList<Post> findAllPosts();
	public Post insertNewPost(int poster_id, String timePosted, String title, String description, int postType, String tags);
	public ArrayList<Post> findAllBusinessPosts();
	public Boolean deleteSinglePost(int poster_id, String title);
	public Boolean deleteAllUserPosts(int poster_id);
}
