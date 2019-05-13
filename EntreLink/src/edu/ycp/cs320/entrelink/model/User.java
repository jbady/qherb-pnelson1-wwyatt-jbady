package edu.ycp.cs320.entrelink.model;

import java.util.ArrayList;

public class User {
	
	// initial values
	private String username;
	private String password;
	private String userFirstName;
	private String userLastName;
	private String email;
	private int userId;
	
	// user-customizable values
	private String profilePic;
	private String website;
	private String bio;
	private String userType;
	private String major;
	private String status;
	private String interests;
	private String skills;	
	
	public User() {
	}
	
	public User(String username, String password, String userFirstName, String userLastName, String email,
			String userType) {
		this.username = username;
		this.password = password;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.email = email;
		this.userType = userType;
		profilePic = "https://imgur.com/a/HmmZUYX";
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	public String getBio() {
		return bio;
	}
	
	public void setWebsite(String website) {
		this.website = website;
	}
	
	public String getWebsite() {
		return website;
	}
	
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	
	public String getProfilePic() {
		return profilePic;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public String getUserType() {
		return userType;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	
	public String getUserFirstName() {
		return this.userFirstName;
	}
	
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	
	public String getUserLastName() {
		return this.userLastName;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public void setMajor(String major) {
		this.major = major;
	}
	
	public String getMajor() {
		return this.major;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setInterests(String interests) {
		this.interests = interests;
	}
	
	public String getInterests() {
		return interests;
	}
	
	public void setSkills(String skills) {
		this.skills = skills;
	}
	
	public String getSkills() {
		return skills;
	}
}
