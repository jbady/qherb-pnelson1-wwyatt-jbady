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
		
		controller.deleteUser(JAdoe.getUsername(), JAdoe.getEmail());
	}
	
	@Test
	public void testChangeUserBio(){
		User JAdoe = controller.createNewUser("JAdoe", "theMrs", "Jane", "Doe", "JAdoe@gmail.com", "student", "i'm his wife", "Art", "married", "the children", "basket weaving");
		User testUser = controller.editUserBio(JAdoe.getUsername(), "THIS IS A TEST BIO");
		
		assertEquals(testUser.getUsername(), JAdoe.getUsername());
		assertEquals(testUser.getBio(), "THIS IS A TEST BIO");
		
		controller.deleteUser(testUser.getUsername(), testUser.getEmail());
	}
	
	@Test
	public void testChangeUserPic(){
		User JAdoe = controller.createNewUser("JAdoe", "theMrs", "Jane", "Doe", "JAdoe@gmail.com", "student", "i'm his wife", "Art", "married", "the children", "basket weaving");
		User testUser = controller.editUserPic(JAdoe.getUsername(), "THIS IS A TEST PIC");
		
		assertEquals(testUser.getUsername(), JAdoe.getUsername());
		assertEquals(testUser.getProfilePic(), "THIS IS A TEST PIC");
		
		controller.deleteUser(testUser.getUsername(), testUser.getEmail());
	}
	@Test
	public void testChangeUserWebsite(){
		User JAdoe = controller.createNewUser("JAdoe", "theMrs", "Jane", "Doe", "JAdoe@gmail.com", "student", "i'm his wife", "Art", "married", "the children", "basket weaving");
		User testUser = controller.editUserWebsite(JAdoe.getUsername(), "THIS IS A TEST WEBSITE");
		
		assertEquals(testUser.getUsername(), JAdoe.getUsername());
		assertEquals(testUser.getWebsite(), "THIS IS A TEST WEBSITE");
		
		controller.deleteUser(testUser.getUsername(), testUser.getEmail());
	}
	@Test
	public void testChangeUserMajor(){
		User JAdoe = controller.createNewUser("JAdoe", "theMrs", "Jane", "Doe", "JAdoe@gmail.com", "student", "i'm his wife", "Art", "married", "the children", "basket weaving");
		User testUser = controller.editUserMajor(JAdoe.getUsername(), "THIS IS A TEST MAJOR");
		
		assertEquals(testUser.getUsername(), JAdoe.getUsername());
		assertEquals(testUser.getMajor(), "THIS IS A TEST MAJOR");
		
		controller.deleteUser(testUser.getUsername(), testUser.getEmail());
	}
	@Test
	public void testChangeUserStatus(){
		User JAdoe = controller.createNewUser("JAdoe", "theMrs", "Jane", "Doe", "JAdoe@gmail.com", "student", "i'm his wife", "Art", "married", "the children", "basket weaving");
		User testUser = controller.editUserStatus(JAdoe.getUsername(), "THIS IS A TEST STATUS");
		
		assertEquals(testUser.getUsername(), JAdoe.getUsername());
		assertEquals(testUser.getStatus(), "THIS IS A TEST STATUS");
		
		controller.deleteUser(testUser.getUsername(), testUser.getEmail());
	}
	@Test
	public void testChangeUserInterest(){
		User JAdoe = controller.createNewUser("JAdoe", "theMrs", "Jane", "Doe", "JAdoe@gmail.com", "student", "i'm his wife", "Art", "married", "the children", "basket weaving");
		User testUser = controller.editUserInterests(JAdoe.getUsername(), "THIS IS A TEST INTEREST");
		
		assertEquals(testUser.getUsername(), JAdoe.getUsername());
		assertEquals(testUser.getInterests(), "THIS IS A TEST INTEREST");
		
		controller.deleteUser(testUser.getUsername(), testUser.getEmail());
	}
	@Test
	public void testChangeUserSkills(){
		User JAdoe = controller.createNewUser("JAdoe", "theMrs", "Jane", "Doe", "JAdoe@gmail.com", "student", "i'm his wife", "Art", "married", "the children", "basket weaving");
		User testUser = controller.editUserSkills(JAdoe.getUsername(), "THIS IS A TEST SKILLS");
		
		assertEquals(testUser.getUsername(), JAdoe.getUsername());
		assertEquals(testUser.getSkills(), "THIS IS A TEST SKILLS");
		
		controller.deleteUser(testUser.getUsername(), testUser.getEmail());
	}
}
