package edu.ycp.cs320.entrelink.queries;

import java.util.List;
import java.util.Scanner;

import edu.ycp.cs320.entrelink.userdb.persist.DerbyDatabase;
import edu.ycp.cs320.entrelink.model.User;
import edu.ycp.cs320.entrelink.userdb.persist.DatabaseProvider;
import edu.ycp.cs320.entrelink.userdb.persist.IDatabase;

public class UserInfoByUsername {
	public static void main(String[] args) throws Exception {
		Scanner keyboard = new Scanner(System.in);
		
		System.out.print("Enter a user's username or email: ");
		String name = keyboard.nextLine();
		
		// get the DB instance and execute transaction
		IDatabase db = new DerbyDatabase();
		
		User user = db.findUserByEmailOrUsername(name);
		
		//check if anything was returned
		if (user.getUserLastName().equals(null)) {
			System.out.println("No users found with the username or email <" + name + ">");
		}
		else {
			System.out.println("---User Info for " + user.getUsername() + "---" +
					"\nFirst Name: " + user.getUserFirstName() +
					"\nLast Name:  " + user.getUserLastName() + 
					"\nPassword:   " + user.getPassword() + 
					"\nE-mail:     " + user.getEmail() + 
					"\nUser Type:  " + user.getUserType());
		}
	}
}
