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
}
