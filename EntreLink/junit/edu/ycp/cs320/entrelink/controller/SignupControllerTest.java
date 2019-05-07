package edu.ycp.cs320.entrelink.controller;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.entrelink.controller.LoginController;
import edu.ycp.cs320.entrelink.model.NewUser;
import edu.ycp.cs320.entrelink.model.User;

public class SignupControllerTest {
	private NewUser model;
	private SignupController controller;
	
	@Before
	public void setUp() {
		model = new NewUser("EggMan2", "eggs@ycp.edu", "eggs@ycp.edu", "eggdaddy9", "eggdaddy9", "Jerry", "Egg", "Student");
		controller = new SignupController();
		controller.setModel(model);
	}
	
	@Test
	public void testVerifyEmailsAreSameWhenSame() {
		assertEquals(model.getNewEmail(), model.getConfirmEmail());
		assertTrue(controller.verifyEmailsAreSame());
	}
	
	@Test
	public void testVerifyEmailsAreSameWhenDifferent() {
		model.setConfirmEmail("milk@ycp.edu");
		assertNotEquals(model.getNewEmail(), model.getConfirmEmail());
		assertFalse(controller.verifyEmailsAreSame());
	}
	
	@Test
	public void testVerifyPasswordsAreSameWhenSame() {
		assertEquals(model.getNewPassword(), model.getConfirmPassword());
		assertTrue(controller.verifyPasswordsAreSame());
	}
	
	@Test
	public void testVerifyPasswordsAreSameWhenDifferent() {
		model.setConfirmPassword("eggmommy9");
		assertNotEquals(model.getNewPassword(), model.getConfirmPassword());
		assertFalse(controller.verifyPasswordsAreSame());
	}
	
	@Test
	public void testVerifyEmailIsValidWhenValid() {
		String testEmail = model.getNewEmail();
		Integer emailLength = testEmail.length();
		String getExtension = testEmail.substring(emailLength-8, emailLength);
		assertEquals(getExtension, "@ycp.edu");
		assertTrue(controller.verifyEmailIsValid());
	}
	
	@Test
	public void testVerifyEmailIsValidWhenInvalid() {
		model.setNewEmail("eggs@gmail.com");
		model.setConfirmEmail("eggs@gmail.com");
		String testEmail = model.getNewEmail();
		Integer emailLength = testEmail.length();
		String getExtension = testEmail.substring(emailLength-8, emailLength);
		assertNotEquals(getExtension, "@ycp.edu");
		assertFalse(controller.verifyEmailIsValid());
	}
}
