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
		assertEquals(posts.get(0).getPostId(), 1);	
		assertEquals(posts.get(0).getPosterId(), 2);
		
	}
	
	@Test
	public void testSearchPostsByUsername() {
		
		ArrayList<Post> jPosts = controller.searchPostsByUserName("jbady");
		ArrayList<Post> pPosts = controller.searchPostsByUserName("pnelson1");
		assertEquals(jPosts.size(), 2);
		assertEquals(jPosts.get(0).getTitle(), "Two-legged Chair");
		assertEquals(jPosts.get(1).getTimePosted(), "05 May 2019, 10:52 pm");
		assertEquals(pPosts.size(), 2);
		assertEquals(pPosts.get(0).getTitle(), "Corn Simulator");
		assertEquals(pPosts.get(0).getTimePosted(), "12 April 2019, 12:27 pm");
	}
	
	@Test
	public void testInsertNewPost() {
		Post tPost = new Post();
		tPost.setDescription("This post is a test post, don't mind me");
		tPost.setTitle("TEST POST");
		tPost.setPosterId(1);
		tPost.setTimePosted("5 April 2019, 08:01 pm");
		tPost.setPostType(0);
		tPost.setTags("testing corn soup");
		
		Post tNewPost = controller.createNewPost(tPost.getPosterId(), tPost.getTimePosted(), tPost.getTitle(), tPost.getDescription(), tPost.getPostType(), tPost.getTags());
		
		assertEquals(tPost.getDescription(), tNewPost.getDescription());
		assertEquals(tPost.getTitle(), tNewPost.getTitle());
		assertEquals(tPost.getPosterId(), tNewPost.getPosterId());
		assertEquals(tPost.getTimePosted(), tNewPost.getTimePosted());
		assertEquals(tPost.getPostType(), tNewPost.getPostType());
		assertEquals(tPost.getTags(), tNewPost.getTags());
		
		controller.deleteSinglePost(tNewPost.getPosterId(), tNewPost.getTitle());
	}
	
	@Test
	public void testDeleteAllPostsByUser() {
		Post tPost1 = new Post();
		tPost1.setDescription("This post is a test post1, don't mind me");
		tPost1.setTitle("TEST POST1");
		tPost1.setPosterId(1);
		tPost1.setTimePosted("5 April 2019, 08:01 pm");
		tPost1.setPostType(0);
		
		Post tPost2 = new Post();
		tPost2.setDescription("This post is a test post2, don't mind me");
		tPost2.setTitle("TEST POST2");
		tPost2.setPosterId(1);
		tPost2.setTimePosted("5 April 2019, 08:01 pm");
		tPost2.setPostType(0);
		
		Post tPost3 = new Post();
		tPost3.setDescription("This post is a test post3, don't mind me");
		tPost3.setTitle("TEST POST3");
		tPost3.setPosterId(1);
		tPost3.setTimePosted("5 April 2019, 08:01 pm");
		tPost3.setPostType(0);
		
		controller.createNewPost(tPost1.getPosterId(), tPost1.getTimePosted(), tPost1.getTitle(), tPost1.getDescription(), tPost1.getPostType(), "");
		controller.createNewPost(tPost2.getPosterId(), tPost2.getTimePosted(), tPost2.getTitle(), tPost2.getDescription(), tPost2.getPostType(), "");
		controller.createNewPost(tPost3.getPosterId(), tPost3.getTimePosted(), tPost3.getTitle(), tPost3.getDescription(), tPost3.getPostType(), "");
		
		ArrayList<Post> jPosts = controller.searchPostsByUserName("jbady");
		
		assertEquals(jPosts.size(), 5);
		
		controller.deleteAllPosts(jPosts.get(0).getPosterId());
		
		ArrayList<Post>jPosts2 = controller.searchPostsByUserName("jbady");
		
		assertEquals(jPosts.size(), 5);
		
		controller.createNewPost(jPosts.get(0).getPosterId(), jPosts.get(0).getTimePosted(), jPosts.get(0).getTitle(), jPosts.get(0).getDescription(), jPosts.get(0).getPostType(), "");
		controller.createNewPost(jPosts.get(1).getPosterId(), jPosts.get(1).getTimePosted(), jPosts.get(1).getTitle(), jPosts.get(1).getDescription(), jPosts.get(1).getPostType(), "");
	}
}
