package edu.ycp.cs320.entrelink.model;

import java.util.ArrayList;

public class PostList {
	
	ArrayList<Post> postList = new ArrayList<Post>();
	
	public PostList() {
		
	}
	
	public ArrayList<Post> getPostList(){
		return this.postList;
	}
	
	public void addPost(Post post) {
		postList.add(post);
	}
	
	public void removePost(Post post) {
		for(int i = 0; i < postList.size(); i++) {
			if(postList.get(i).equals(post)) {
				postList.remove(i);
			}
		}
	}
	
}
