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
	
	// search for posts by tag by iterating through tags of existing posts
	// and comparing to iterations through desired tags array list
	public ArrayList<Post> findPostsByTags(ArrayList<String> tags){
		
		ArrayList<Post> postsContainingTags = new ArrayList<Post>();
		
		// This is no longer working because I updated the tags functions
		
		/*for(int i = 0; i < postList.size(); i++) {
			String tagsInPosts = postList.get(i).getTags();
			for(int j = 0; j < tagsInPosts.size(); j++) {
				for(int k = 0; k < tags.size(); k++) {
					if(tagsInPosts.get(j).toLowerCase().equals(tags.get(k).toLowerCase())) {
						postsContainingTags.add(postList.get(i));
					}
				}
			}
		}*/
		return postsContainingTags;
	}
	
	// the next two literally explain themselves
	public void addPostToPostList(Post post) {
		postList.add(post);
	}
	
	public void addMultiplePostsToPostList(ArrayList<Post> posts) {
		postList.addAll(posts);
	}
	
	// find post by title, if an existing post contains the character sequence
	// add it to an array list of posts and return it
	public ArrayList<Post> findPostsByTitle(String title){
		
		ArrayList<Post> postsWithTitle = new ArrayList<Post>();
		
		for(int i = 0; i < postList.size(); i++) {
			if(postList.get(i).getTitle().toLowerCase().contains(title.toLowerCase())) {
				postsWithTitle.add(postList.get(i));
			}
		}
		return postsWithTitle;
	}
	
	// find post by user's name, if an existing post contains the character sequence
	// within either the user's first name or last name, add it
	public ArrayList<Post> findPostsByUserName(String name){
		
		ArrayList<Post> postsWithUser = new ArrayList<Post>();
		
		for(int i = 0; i < postList.size(); i++) {
			if(postList.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
				postsWithUser.add(postList.get(i));
			}
		}
		return postsWithUser;
	}

	@Override
	public User insertNewUser(String username, String password, String userFirstName, String userLastName, String email,
			String userType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Post> findAllPosts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post insertNewPost(int poster_id, String timePosted, String title, String description, int postType, String tags) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Post> findAllBusinessPosts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteSinglePost(int poster_id, String title) {
		return null;
		// TODO Auto-generated method stub
	}

	@Override
	public Boolean deleteAllUserPosts(int poster_id) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public User changeUserBio(String username, String bio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User changeUserPic(String username, String pic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User changeUserWebsite(String username, String website) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User changeUserMajor(String username, String major) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User changeUserStatus(String username, String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User changeUserInterests(String username, String interests) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User changeUserSkills(String username, String skills) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteUser(String username, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
