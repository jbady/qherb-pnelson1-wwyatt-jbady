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
}
