package edu.ycp.cs320.entrelink.controller;

import java.util.List;

import edu.ycp.cs320.entrelink.model.User;
import edu.ycp.cs320.entrelink.userdb.persist.DatabaseProvider;
import edu.ycp.cs320.entrelink.userdb.persist.DerbyDatabase;
import edu.ycp.cs320.entrelink.userdb.persist.IDatabase;
import com.mkyong.regex.EmailValidator;

public class SignupController {
	
	private User model;
	
	public void setModel(User model) {
		this.model = model;
	}
	
	
	IDatabase db = new DerbyDatabase();
	User getUser;
	
	public boolean verifyIsNewUser() {
		if(model != null) {
			if(db.findUserByEmailOrUsername(model.getUsername()).equals(null)) {
				if(db.findUserByEmailOrUsername(model.getEmail()).equals(null)) {
					return true;
				}
			}
		}
	return false;
	}
	
	public boolean verifyEmailIsValid() {
		String testEmail = model.getNewEmail();
		EmailValidator validate = new EmailValidator();
		if(validate.validate(testEmail)) {

			return true;
		}
		return false;
	}
	
	public User getModel() {
		return model;
	}
	
	public User createNewUser(String username, String password, String userFirstName, String userLastName, String email, String userType) {
		User user = new User();
		
		System.out.println("Inserting new user...");
		
		user = db.insertNewUser(username, password, userFirstName, userLastName, email, userType);
		
		if(user == null) {
			System.out.println("Post was NOT inserted. You wrote bad code.");
		}
		else {
			System.out.println("Successfully inserted '" + userFirstName + " " + userLastName + "' as a new user.");
		}
		
		return user;
	}
	
}
