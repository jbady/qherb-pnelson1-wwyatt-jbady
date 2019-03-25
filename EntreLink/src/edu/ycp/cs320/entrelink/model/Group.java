package edu.ycp.cs320.entrelink.model;

import java.util.ArrayList;

public class Group {
	
	ArrayList<User> members = new ArrayList<User>();
	ArrayList<Message> messages = new ArrayList<Message>();
	
	public Group() {
		
	}
	
	// get members
	public ArrayList<User> getMembers(){
		return this.members;
	}
	// get messages
	public ArrayList<Message> getMessages(){
		return this.messages;
	}
	
	public void addUser(User user) {
		this.members.add(user);
	}
	
	public void removeUser(User user) {
		for(int i = 0; i < members.size(); i++) {
			if(members.get(i).equals(user)) {
				members.remove(i);
			}
		}
	}
	
	public void addMessage(Message message) {
		this.messages.add(message);
	}
	
	public void removeMessage(Message message) {
		for(int i = 0; i < members.size(); i++) {
			if(messages.get(i).equals(message)) {
				messages.remove(i);
			}
		}
	}
	
}
