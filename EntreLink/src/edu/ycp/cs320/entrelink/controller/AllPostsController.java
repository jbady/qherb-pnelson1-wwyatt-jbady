package edu.ycp.cs320.entrelink.controller;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.entrelink.model.Post;
import edu.ycp.cs320.entrelink.userdb.persist.DatabaseProvider;
import edu.ycp.cs320.entrelink.userdb.persist.DerbyDatabase;
import edu.ycp.cs320.entrelink.userdb.persist.IDatabase;


public class AllPostsController {

	private IDatabase db = null;

	public AllPostsController() {
		
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
	}

	public ArrayList<Post> getAllPosts() {
		
		// get the list of (Author, Book) pairs from DB
		List<Post> postList = db.findAllPosts();
		ArrayList<Post> posts = null;
		
		if (postList.isEmpty()) {
			System.out.println("No authors found in library");
			return null;
		}
		else {
			posts = new ArrayList<Post>();
			for (Post post : postList) {
				posts.add(post);
				//System.out.println(author.getLastname() + ", " + author.getFirstname());
			}			
		}
		
		// return authors for this title
		return posts;
	}
}