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
			String userType) {
		
			System.out.print("user '" + username + "' is being inserted");
			
			newUser = db.insertNewUser(username, password, userFirstName, userLastName, email, userType);
			if(newUser.getEmail()!= null) {
				model = newUser;
				System.out.print("user '" + username + "' has been inputed");
				
			}else {
				System.out.println("user hasn't been inserted");
			}
		
		return newUser;
	}
	public boolean deleteUser(String username, String email) {
		System.out.println("user'"+ username + "' is being deleted");
		
		Boolean done = db.deleteUser(username, email);
		
		if(done) {
			System.out.println("user '" + username + "' has been deleted");
		}else {
			System.out.println("user was not deleted");
		}
		
		return done;
	}
	public User editUserBio(String username, String bio) {
		System.out.println("changing user's bio...");
		
		newUser = db.changeUserBio(username, bio);
		
		if(newUser!=null) {
			System.out.println("system has changed user's bio");
			return newUser;
		}else {
			System.out.println("bio was not changed");
			return null;
		}
	}
	public User editUserPic(String username, String pic) {
		System.out.println("changing user's bio...");
		
		newUser = db.changeUserPic(username, pic);
		
		if(newUser!=null) {
			System.out.println("system has changed user's bio");
			return newUser;
		}else {
			System.out.println("bio was not changed");
			return null;
		}
	}
	public User editUserWebsite(String username, String site) {
		System.out.println("changing user's bio...");
		
		newUser = db.changeUserWebsite(username, site);
		
		if(newUser!=null) {
			System.out.println("system has changed user's bio");
			return newUser;
		}else {
			System.out.println("bio was not changed");
			return null;
		}
	}
	public User editUserMajor(String username, String Major) {
		System.out.println("changing user's bio...");
		
		newUser = db.changeUserMajor(username, Major);
		
		if(newUser!=null) {
			System.out.println("system has changed user's bio");
			return newUser;
		}else {
			System.out.println("bio was not changed");
			return null;
		}
	}
	public User editUserStatus(String username, String status) {
		System.out.println("changing user's bio...");
		
		newUser = db.changeUserStatus(username, status);
		
		if(newUser!=null) {
			System.out.println("system has changed user's bio");
			return newUser;
		}else {
			System.out.println("bio was not changed");
			return null;
		}
	}
	public User editUserInterests(String username, String interests) {
		System.out.println("changing user's bio...");
		
		newUser = db.changeUserInterests(username, interests);
		
		if(newUser!=null) {
			System.out.println("system has changed user's bio");
			return newUser;
		}else {
			System.out.println("bio was not changed");
			return null;
		}
	}public User editUserSkills(String username, String skills) {
		System.out.println("changing user's bio...");
		
		newUser = db.changeUserSkills(username, skills);
		
		if(newUser!=null) {
			System.out.println("system has changed user's bio");
			return newUser;
		}else {
			System.out.println("bio was not changed");
			return null;
		}
	}
	
	public User getModel() {
		return model;
	}
}
