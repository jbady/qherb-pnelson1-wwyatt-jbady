package edu.ycp.cs320.entrelink.controller;

import java.util.ArrayList;

import edu.ycp.cs320.entrelink.model.Post;
import edu.ycp.cs320.entrelink.userdb.persist.DerbyDatabase;

public class PostController {

	private DerbyDatabase db;
	Post post;
	
	public PostController() {
		db = new DerbyDatabase();
	}
	public ArrayList<Post> searchPostsByTitle(String title) {
		ArrayList<Post> posts = new ArrayList<Post>();
		
		System.out.println("finding posts like title '" + title + "'");
		posts = db.findPostsByTitle(title);
		
		if(posts == null) {
			System.out.println("no posts found");
		}else {
			System.out.println("found posts");
		}
		return posts;
	}
	public ArrayList<Post> searchPostsByUserName(String userName){
		ArrayList<Post> posts = new ArrayList<Post>();
		
		System.out.println("Finding posts from user: '" + userName + "'");
		posts = db.findPostsByUserName(userName);
		
		if(posts == null) {
			System.out.println("no posts found");
		}else {
			System.out.println("found " + posts.size() + " posts");
		}
		return posts;
	}
	public Post createNewPost(int poster_id, String timePosted, String title, String description, int postType) {
		Post post = new Post();
		System.out.println("inserting new post...");
		
		post = db.insertNewPost(poster_id, timePosted, title, description, postType);
		
		if(post == null) {
			System.out.println("post was not inserted");
		}else {
			System.out.println("inserted '" + post.getTitle() + "'");
		}
		return post;
	}
}
