package edu.ycp.cs320.entrelink.controller;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.entrelink.controller.LoginController;
import edu.ycp.cs320.entrelink.model.User;

public class LoginControllerTest {
	private User model;
	private LoginController controller;
	
	@Before
	public void setUp() {
		model = new User();
		controller = new LoginController();
	}
	
	@Test
	public void testSetModel() {
		model.setUserFirstName("John");
		model.setUserLastName("Doe");
		model.setEmail("jdoe@ycp.edu");
		model.setPassword("ILikeRocks");
		model.setUsername("jdoe");
		
		controller.setModel(model);
		
		assertEquals("John", controller.getModel().getUserFirstName());
		assertEquals("Doe", controller.getModel().getUserLastName());
		assertEquals("jdoe@ycp.edu", controller.getModel().getEmail());
		assertEquals("ILikeRocks", controller.getModel().getPassword());
		assertEquals("jdoe", controller.getModel().getUsername());
	}
	
	@Test
	public void testVerifyUserExists() {
	
		// tests that you can log in with email and password
		
		// John Doe
		controller.setModel(model);
		model.setPassword("ILikeRocks");
		model.setEmail("jdoe@ycp.edu");
		

		assertTrue(controller.verifyUser());
		
		// Patrick Nelson
		model.setPassword("toothbrush");
		model.setEmail("pnelson1@ycp.edu");
		
		controller.setModel(model);

		assertTrue(controller.verifyUser());
		
		// Jason Bady
		model.setPassword("fingernail");
		model.setEmail("jbady@ycp.edu");
		
		controller.setModel(model);

		assertTrue(controller.verifyUser());
		
		// William Wyatt
		model.setPassword("RockSalt");
		model.setEmail("wwyatt@ycp.edu");
		
		controller.setModel(model);

		assertTrue(controller.verifyUser());
		
		
		// Quintin Herp
		model.setPassword("3legs");
		model.setEmail("qherb@ycp.edu");
		
		controller.setModel(model);

		assertTrue(controller.verifyUser());
		
		
		// Professor Hake
		model.setPassword("BDConsultant");
		model.setEmail("djhake@ycp.edu");
		
		controller.setModel(model);

		assertTrue(controller.verifyUser());
		
	}
	
	@Test
	public void testVerifyUserDoesNotExist() {
		// Tests with everything null
		assertFalse(controller.verifyUser());
		
		// Should still be null, but this time has a null model
		controller.setModel(model);
		assertFalse(controller.verifyUser());
		
		// Usernames that do not exist in the database
		model.setUsername("poop");
		model.setPassword("poop");
		controller.setModel(model);
		assertFalse(controller.verifyUser());
		
		model.setUsername("jake");
		model.setPassword(null);
		controller.setModel(model);
		assertFalse(controller.verifyUser());
		
		// E-mails that do not exist in the database
		model.setEmail("pnelson1@ycp.poo");
		model.setPassword("toothbrush");
		controller.setModel(model);
		assertFalse(controller.verifyUser());
		
		model.setEmail("qherp@ycp.edu");
		model.setPassword(null);
		controller.setModel(model);
		assertFalse(controller.verifyUser());
	}
	
	@Test
	public void testVerifyUserInvalidPassword() {
		// E-mails with invalid passwords
		model.setEmail("pnelson1@ycp.edu");
		model.setPassword("toothbrushes");
		controller.setModel(model);
		assertFalse(controller.verifyUser());
		
		model.setEmail("qherb@ycp.edu");
		model.setPassword("3leg");
		controller.setModel(model);
		assertFalse(controller.verifyUser());
		
		// Usernames with invalid passwords
		model.setUsername("jbady");
		model.setPassword("waffle");
		controller.setModel(model);
		assertFalse(controller.verifyUser());
		
		model.setUsername("wwyatt");
		model.setPassword("cornbread");
		controller.setModel(model);
		assertFalse(controller.verifyUser());
	}
}
