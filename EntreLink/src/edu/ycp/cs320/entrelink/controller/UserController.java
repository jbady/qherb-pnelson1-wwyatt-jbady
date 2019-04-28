package edu.ycp.cs320.entrelink.controller;

import edu.ycp.cs320.entrelink.model.User;
import edu.ycp.cs320.entrelink.userdb.persist.DerbyDatabase;
import edu.ycp.cs320.entrelink.userdb.persist.IDatabase;

public class UserController {
	
	private User model;
	
	public void setModel(User model) {
		this.model = model;
	}
	
	IDatabase db = new DerbyDatabase();
	User newUser;
	
	public User createNewUser(String username, String password, String userFirstName, String userLastName, String email,
			String userType, String bio, String major, String status, String interest, String skills) {
		
			System.out.print("user '" + username + "' is being inserted");
			
			newUser = db.insertNewUser(username, password, userFirstName, userLastName, email, userType, bio, major, status, interest, skills);
			if(newUser.getEmail()!= null) {
				model = newUser;
				System.out.print("user '" + username + "' has been inputed");
				
			}else {
				System.out.println("user hasn't been inserted");
			}
		
		return newUser;
	}
	
	public User getModel() {
		return model;
	}
}
