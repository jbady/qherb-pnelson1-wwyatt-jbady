package edu.ycp.cs320.entrelink.controller;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.entrelink.controller.PostController;
import edu.ycp.cs320.entrelink.model.Post;
import edu.ycp.cs320.entrelink.userdb.persist.DerbyDatabase;

public class PostControllerTest {
	private PostController controller;
	private DerbyDatabase db;
	
	@Before
	public void setUp() {
		controller = new PostController();
		db = new DerbyDatabase();
	}
		
	@Test
	public void testSearchPostsByTitle() {
	
		//db.createTables();
		ArrayList<Post> posts = controller.searchPostsByTitle("Corn");
		assertEquals(posts.get(0).getTitle(), "Corn Simulator");
		assertEquals(posts.get(0).getName(), "Patrick Nelson");
		assertEquals(posts.get(0).getPostId(), 1);	
		
	}
	
	@Test
	public void testSearchPostsByUsername() {
		
		ArrayList<Post> jPosts = controller.searchPostsByUserName("jbady");
		ArrayList<Post> pPosts = controller.searchPostsByUserName("pnelson1");
		assertEquals(jPosts.size(), 2);
		assertEquals(jPosts.get(1).getTitle(), "Ringed PopSocket");
		assertEquals(jPosts.get(0).getTimePosted(), 245);
		assertEquals(pPosts.size(), 1);
		assertEquals(pPosts.get(0).getTitle(), "Corn Simulator");
		assertEquals(pPosts.get(0).getTimePosted(), 1227);
	}
	
	@Test
	public void testInsertNewPost() {
		Post tPost = new Post();
		tPost.setDescription("This post is a test post, don't mind me");
		tPost.setTitle("The test post");
		tPost.setPosterId(1);
		tPost.setTimePosted("5 April 2019, 08:01 pm");
		
		Post tNewPost = controller.createNewPost(tPost.getPosterId(), tPost.getTimePosted(), tPost.getTitle(), tPost.getDescription());
		
		assertEquals(tPost.getDescription(), tNewPost.getDescription());
		assertEquals(tPost.getTitle(), tNewPost.getTitle());
		assertEquals(tPost.getPosterId(), tNewPost.getPosterId());
		assertEquals(tPost.getTimePosted(), tNewPost.getTimePosted());
	}
}
