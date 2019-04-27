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
	
	public User createNewUser(String username, String firstName, String lastName, String email, String password, String userType) {
		if(model!=null) {
			newUser = db.createNewUser(username, firstName, lastName, email, password, userType);
			if(newUser.getEmail()!= null) {
				model = newUser;
			}
		}
		return newUser;
	}
	
	public User getModel() {
		return model;
	}
}
