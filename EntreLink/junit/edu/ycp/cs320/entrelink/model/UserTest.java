package edu.ycp.cs320.entrelink.model;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.entrelink.model.User;
public class UserTest {
	private User model;
	
	@Before
	public void setUp() {
		model = new User();
	}
	
	@Test
	public void testSetGetUsername() {
		model.setUsername("jdoe");
		assertEquals("jdoe", model.getUsername());

		model.setUsername("pnelson1");
		assertEquals("pnelson1", model.getUsername());
		
		model.setUsername("qherb");
		assertEquals("qherb", model.getUsername());
		
		model.setUsername("sauce");
		assertEquals("sauce", model.getUsername());
		
		model.setUsername("cactus");
		assertEquals("cactus", model.getUsername());
	}
	
	@Test
	public void testSetGetPassword() {
		model.setPassword("waffle");
		assertEquals("waffle", model.getPassword());

		model.setPassword("toothbrush");
		assertEquals("toothbrush", model.getPassword());

		model.setPassword("teethbrush");
		assertEquals("teethbrush", model.getPassword());

		model.setPassword("toofbrush");
		assertEquals("toofbrush", model.getPassword());
	}
	
	@Test
	public void testSetGetFirstname() {
		model.setUserFirstName("Patrick");
		assertEquals("Patrick", model.getUserFirstName());

		model.setUserFirstName("Quintin");
		assertEquals("Quintin", model.getUserFirstName());

		model.setUserFirstName("Jason");
		assertEquals("Jason", model.getUserFirstName());

		model.setUserFirstName("William");
		assertEquals("William", model.getUserFirstName());
	}
	
	@Test
	public void testSetGetLastname() {
		model.setUserLastName("Nelson");
		assertEquals("Nelson", model.getUserLastName());

		model.setUserLastName("Herb");
		assertEquals("Herb", model.getUserLastName());

		model.setUserLastName("Bady");
		assertEquals("Bady", model.getUserLastName());

		model.setUserLastName("Wyatt");
		assertEquals("Wyatt", model.getUserLastName());
	}
	
	@Test
	public void testSetGetEmail() {
		model.setEmail("pnelson1@ycp.edu");
		assertEquals("pnelson1@ycp.edu", model.getEmail());

		model.setEmail("qherb@ycp.edu");
		assertEquals("qherb@ycp.edu", model.getEmail());

		model.setEmail("jbady@ycp.edu");
		assertEquals("jbady@ycp.edu", model.getEmail());

		model.setEmail("wwyatt@ycp.edu");
		assertEquals("wwyatt@ycp.edu", model.getEmail());
	}
	
	@Test
	public void testSetGetIsLoggedIn() {
		assertFalse(model.getIsLoggedIn());
		
		model.setIsLoggedIn(true);
		
		assertTrue(model.getIsLoggedIn());
		
		model.setIsLoggedIn(false);
		
		assertFalse(model.getIsLoggedIn());
	}
	
}
