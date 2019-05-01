package edu.ycp.cs320.entrelink.model;

public class NewUser {
	
	private String newUsername;
	private String newEmail;
	private String confirmEmail;
	private String newPassword;
	private String confirmPassword;
	private String firstname;
	private String lastname;
	private String accountType;
	
	
	public NewUser() {}
	
	public NewUser(String newUsername, String newEmail, String confirmEmail, String newPassword, String confirmPassword, String firstname, String lastname, String accountType) {
		this.newUsername = newUsername;
		this.newEmail = newEmail;
		this.confirmEmail = confirmEmail;
		this.newPassword = newPassword;
		this.confirmPassword = confirmPassword;
		this.firstname = firstname;
		this.lastname = lastname;
		this.accountType = accountType;
	}
	
	// getters
	public String getNewUsername() {
		return this.newUsername;
	}
	public String getNewEmail() {
		return this.newEmail;
	}
	public String getConfirmEmail() {
		return this.confirmEmail;
	}
	public String getNewPassword() {
		return this.newPassword;
	}
	public String getConfirmPassword() {
		return this.confirmPassword;
	}
	public String getFirstname() {
		return this.firstname;
	}
	public String getLastname() {
		return this.lastname;
	}
	public String getAccoutType() {
		return this.accountType;
	}
	
	// setters
	public void setNewUsername(String newUsername) {
		this.newUsername = newUsername;
	}
	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}
	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword; 
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
}
