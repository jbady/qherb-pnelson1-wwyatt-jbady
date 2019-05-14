package edu.ycp.cs320.entrelink.controller;

import java.util.ArrayList;
import java.util.List;

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
	public Post createNewPost(int poster_id, String timePosted, String title, String description, int postType, String tags) {
		Post post = new Post();
		System.out.println("inserting new post...");
		
		post = db.insertNewPost(poster_id, timePosted, title, description, postType, tags);
		
		if(post == null) {
			System.out.println("post was not inserted");
		}else {
			System.out.println("inserted '" + post.getTitle() + "'");
		}
		return post;
	}
	public void deleteSinglePost(int poster_id, String title) {
		System.out.println("deleteing post...");
		
		Boolean done = db.deleteSinglePost(poster_id, title);
		
		if(done) {
			System.out.println("post '" + title + "' by user numer '" + poster_id + "' has been deleted");
		}else {
			System.out.println("post was not deleted");
		}
	}
	public void deleteAllPosts(int poster_id) {
		System.out.println("deleteing all posts by user...");
		
		Boolean done = db.deleteAllUserPosts(poster_id);
		
		if(done) {
			System.out.println("posts by user number '" + poster_id + "' have been deleted");
		}else {
			System.out.println("posts were not deleted");
		}
	}
	public ArrayList<Post> getAllPosts(String postType) {
		
		List<Post> postList;
		
		if (postType.equals("student")) {
			postList = db.findAllPosts();
		}
		else {
			postList = db.findAllBusinessPosts();
		}
		
		ArrayList<Post> posts = null;
		if (postList.isEmpty()) {
			System.out.println("No posts found in library");
			return null;
		}
		else {
			posts = new ArrayList<Post>();
			for (Post post : postList) {
				posts.add(post);
			}			
		}
		
		// return authors for this title
		return posts;
	}
}
