package edu.ycp.cs320.entrelink.userdb.persist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.entrelink.model.Post;
import edu.ycp.cs320.entrelink.model.User;

public class FakeDatabaseTest {
	private User user;
	private IDatabase db;
	
	@Before
	public void setUp() throws IOException {
		user = new User();
		db = new FakeDatabase();
	}
	
	@Test
	public void testTwoIsEqualToThreeMinusOne() {
		int i = 2;
		assertTrue(i == (3-1));
	}
	
	@Test
	public void testFindUserByEmailOrUsernameExists() {
		
		// Tests finding Patrick Nelson by his username
		user = db.findUserByEmailOrUsername("pnelson1");
		assertEquals(user.getEmail(), "pnelson1@ycp.edu");
		assertEquals(user.getPassword(), "toothbrush");
		assertEquals(user.getUserFirstName(), "Patrick");
		assertEquals(user.getUserLastName(), "Nelson");
		assertEquals(user.getUsername(), "pnelson1");
		
		// Tests finding Quintin Herb by his e-mail
		user = db.findUserByEmailOrUsername("qherb@ycp.edu");
		assertEquals(user.getEmail(), "qherb@ycp.edu");
		assertEquals(user.getPassword(), "3legs");
		assertEquals(user.getUserFirstName(), "Quintin");
		assertEquals(user.getUserLastName(), "Herb");
		assertEquals(user.getUsername(), "qherb");
		
		// Tests finding Professor Hake by his username
		user = db.findUserByEmailOrUsername("djhake");
		assertEquals(user.getEmail(), "djhake@ycp.edu");
		assertEquals(user.getPassword(), "BDConsultant");
		assertEquals(user.getUserFirstName(), "Professor");
		assertEquals(user.getUserLastName(), "Hake");
		assertEquals(user.getUsername(), "djhake");
	}
	
	@Test
	public void testFindUserByEmailOrUsernameDoesNotExist() {
		
		// Tests finding a user that does not exist with a username
		user = db.findUserByEmailOrUsername("tacobell");
		assertEquals(user.getEmail(), null);
		assertEquals(user.getPassword(), null);
		assertEquals(user.getUserFirstName(), null);
		assertEquals(user.getUserLastName(), null);
		assertEquals(user.getUsername(), null);
		
		// Tests finding a user that does not exist with another username
		user = db.findUserByEmailOrUsername("pnelson1@ycp.edy");
		assertEquals(user.getEmail(), null);
		assertEquals(user.getPassword(), null);
		assertEquals(user.getUserFirstName(), null);
		assertEquals(user.getUserLastName(), null);
		assertEquals(user.getUsername(), null);
	}
	
	@Test
	public void testFindUserByEmailOrUsernameNoEntry() {
		
		// Nothing was typed in
		user = db.findUserByEmailOrUsername("");
		assertEquals(user.getEmail(), null);
		assertEquals(user.getPassword(), null);
		assertEquals(user.getUserFirstName(), null);
		assertEquals(user.getUserLastName(), null);
		assertEquals(user.getUsername(), null);
		
		// Null input
		user = db.findUserByEmailOrUsername(null);
		assertEquals(user.getEmail(), null);
		assertEquals(user.getPassword(), null);
		assertEquals(user.getUserFirstName(), null);
		assertEquals(user.getUserLastName(), null);
		assertEquals(user.getUsername(), null);
		
	}
	
	@Test
	public void testFindPostsByTags(){
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("eggs");
		tags.add("corn");
		ArrayList<Post> postsWithTags = db.findPostsByTags(tags);
		assertEquals(postsWithTags.get(0).getTitle(), "Corn Simulator");
		
		tags = new ArrayList<String>();
		tags.add("arts");
		postsWithTags = db.findPostsByTags(tags);
		assertTrue(postsWithTags.get(0).getTitle().equals("Two-legged Chair") || postsWithTags.get(0).getTitle().equals("Cheese Grater Phone Case"));
		assertTrue(postsWithTags.get(1).getTitle().equals("Two-legged Chair") || postsWithTags.get(1).getTitle().equals("Cheese Grater Phone Case"));
	}
}
