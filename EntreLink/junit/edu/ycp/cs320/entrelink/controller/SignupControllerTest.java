package edu.ycp.cs320.entrelink.controller;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.entrelink.controller.LoginController;
import edu.ycp.cs320.entrelink.model.User;

public class SignupControllerTest {
	private User model;
	private SignupController controller;
	
	@Before
	public void setUp() {
		model = new User("EggMan2", "eggdaddy9", "Jerry", "Egg", "eggs@ycp.edu", "Student");
		controller = new SignupController();
		controller.setModel(model);
	}
	
	@Test
	public void testVerifyEmailsAreSameWhenSame() {
		assertEquals(model.getEmail(), "eggs@ycp.edu");
	}
	
	@Test
	public void testVerifyEmailsAreSameWhenDifferent() {
		model.setEmail("milk@ycp.edu");
		assertNotEquals(model.getEmail(), "eggs@ycp.edu");
	}
	
	@Test
	public void testVerifyPasswordsAreSameWhenSame() {
		assertEquals(model.getPassword(), "eggdaddy9");
	}
	
	@Test
	public void testVerifyPasswordsAreSameWhenDifferent() {
		model.setPassword("eggmommy9");
		assertNotEquals(model.getPassword(), "eggdaddy9");
	}
	
	@Test
	public void testVerifyEmailIsValidWhenValid() {
		String testEmail = model.getEmail();
		Integer emailLength = testEmail.length();
		String getExtension = testEmail.substring(emailLength-8, emailLength);
		assertEquals(getExtension, "@ycp.edu");
		assertTrue(controller.verifyEmailIsValid());
	}
	
	@Test
	public void testVerifyEmailIsValidWhenInvalid() {
		model.setEmail("eggs@gmail.com");
		String testEmail = model.getEmail();
		Integer emailLength = testEmail.length();
		String getExtension = testEmail.substring(emailLength-8, emailLength);
		assertNotEquals(getExtension, "@ycp.edu");
		assertFalse(controller.verifyEmailIsValid());
	}
}
