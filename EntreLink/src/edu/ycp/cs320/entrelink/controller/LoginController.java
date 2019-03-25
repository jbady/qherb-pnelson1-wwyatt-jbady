package edu.ycp.cs320.entrelink.controller;

import java.util.List;

import edu.ycp.cs320.entrelink.model.User;
import edu.ycp.cs320.entrelink.userdb.persist.DatabaseProvider;
import edu.ycp.cs320.entrelink.userdb.persist.FakeDatabase;
import edu.ycp.cs320.entrelink.userdb.persist.IDatabase;

public class LoginController {
	
	private User model;
	
	public void setModel(User model) {
		this.model = model;
	}
	
	
	IDatabase db = new FakeDatabase();
	User getUser;
	
	public boolean verifyUser() {
		if(model != null) {
			getUser = db.findUserByEmailOrUsername(model.getEmail());
			if(getUser.getUserFirstName() != null) {
				if (getUser.getPassword().equals(model.getPassword())) {
					model = getUser;
					return true;
				}
			}
		}
	return false;
	}
	
	public User getModel() {
		return model;
	}
	
}
