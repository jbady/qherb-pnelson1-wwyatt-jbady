package edu.ycp.cs320.entrelink.model;

import java.util.ArrayList;

public class Post {
	
	// User-related fields
	private int posterId;
	private String name;
	
	// Everything else
	private String timePosted;
	private int postId;
	private String postTitle;
	private String postDescription;
	private ArrayList<String> tags;
	private int postType; //0 = student post; 1 = student skill(s); 2 = business post
	
	public Post() {
		
	}
	public Post(User user, String timePosted, String postTitle, String postDescription, ArrayList<String> tags, int postType) {
		// User-related fields
		posterId = user.getUserId();
		name = user.getUserFirstName() + " " + user.getUserLastName();
		
		// Everything else
		this.timePosted = timePosted;
		this.postTitle = postTitle;
		this.postDescription = postDescription;
		this.tags = tags;
		this.postType = postType;
	}
	
	// Set and get for post type
	public void setPostType(int postType) {
		this.postType = postType;
	}
	
	public int getPostType() {
		return postType;
	}
	
	// Set and get for poster ID
	public void setPosterId(int posterId) {
		this.posterId = posterId;
	}
	public int getPosterId() {
		return posterId;
	}

	// Set and get for name
	public void setName(String firstname, String lastname) {
		name = firstname + " " + lastname;
	}
	public String getName() {
		return name;
	}

	// Set and get for time posted
	public void setTimePosted(String timePosted) {
		this.timePosted = timePosted;
	}
	public String getTimePosted() {
		return timePosted;
	}
	
	// Set and get for post ID
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getPostId() {
		return postId;
	}
	
	// Set and get for post title
	public void setTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getTitle() {
		return postTitle;
	}
	
	// Set and get for post description
	public void setDescription(String description) {
		postDescription = description;
	}
	public String getDescription() {
		return postDescription;
	}
	
	
	// Everything that has to do with tags
	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
	public ArrayList<String> getTags() {
		return tags;
	}
	public void addTags(ArrayList<String> tags) {
		this.tags.addAll(tags);
	}
	
	public void removeTags(ArrayList<Integer> index) {
		for(int i = 0; i < tags.size(); i++) {
			for(int j = 0; j < index.size(); j++) {
				if(i == j) {
					tags.remove(i);
				}
			}
		}
	}
	
}
