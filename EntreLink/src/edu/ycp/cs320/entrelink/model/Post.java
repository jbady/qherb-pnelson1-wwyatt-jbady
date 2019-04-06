package edu.ycp.cs320.entrelink.model;

import java.util.ArrayList;

public class Post {
	
	// User-related fields
	private int posterId;
	private String name;
	
	// Everything else
	private int timePosted;
	private int postId;
	private String postTitle;
	private String postDescription;
	private ArrayList<String> tags;
	
	public Post(User user, int timePosted, int postId, String postTitle, String postDescription, ArrayList<String> tags) {
		// User-related fields
		posterId = user.getUserId();
		name = user.getUserFirstName() + " " + user.getUserLastName();
		
		// Everything else
		this.timePosted = timePosted;
		this.postId = postId;
		this.postTitle = postTitle;
		this.postDescription = postDescription;
		this.tags = tags;
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
	public void setTimePosted(int timePosted) {
		this.timePosted = timePosted;
	}
	public int getTimePosted() {
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
		// TODO: add tags function
		// Will take the new ArrayList and append it on to the old one
	}
	
	public void removeTags(ArrayList<int[]> index) {
		// TODO: add remove tags function
		// Takes in an ArrayList of integers, and will remove the ArrayList elements of selected indexes
	}
	
}
