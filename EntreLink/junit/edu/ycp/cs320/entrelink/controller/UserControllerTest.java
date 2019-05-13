package edu.ycp.cs320.entrelink.controller;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.entrelink.controller.UserController;
import edu.ycp.cs320.entrelink.model.User;
import edu.ycp.cs320.entrelink.userdb.persist.DerbyDatabase;
import edu.ycp.cs320.entrelink.userdb.persist.IDatabase;

public class UserControllerTest {
	private User model;
	private UserController controller;
	private LoginController Lcontroller;
	private DerbyDatabase db;
	
	@Before
	public void setUp() {
		model = new User();
		controller = new UserController();
		Lcontroller = new LoginController();
		db = new DerbyDatabase();
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
	public void testUserCreated() {
	
		// tests that you can log in with email and password
		
		// Jane Doe
		//db.createTables();
		model.setEmail("JAdoe@gmail.com");
		model.setPassword("theMrs");
		User JAdoe = controller.createNewUser("JAdoe", "theMrs", "Jane", "Doe", "JAdoe@gmail.com", "Student");
		assertEquals("JAdoe", JAdoe.getUsername());
		
		
	}
}
