package edu.ycp.cs320.entrelink.userdb.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.entrelink.model.User;

// initializes fake database with users
public class InitialData {
	public static List<User> getUsers() throws IOException {
		List<User> userList = new ArrayList<User>();
		User newUser = new User("pnelson1", "toothbrush", "Patrick", "Nelson", "pnelson1@ycp.edu", "Admin");
		userList.add(newUser);
		newUser = new User("jbady", "fingernail", "Jason", "Bady", "jbady@ycp.edu", "Admin");
		userList.add(newUser);
		newUser = new User("wwyatt", "RockSalt", "William", "Wyatt", "wwyatt@ycp.edu", "Admin");
		userList.add(newUser);
		newUser = new User("qherb", "3legs", "Quintin", "Herb", "qherb@ycp.edu", "Admin");
		userList.add(newUser);
		newUser = new User("djhake", "BDConsultant", "Professor", "Hake", "djhake@ycp.edu", "Business");
		userList.add(newUser);
		newUser = new User("jdoe", "ILikeRocks", "John", "Doe", "jdoe@ycp.edu", "Student");
		userList.add(newUser);
		return userList;
	}
}