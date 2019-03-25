package edu.ycp.cs320.entrelink.model;

import java.util.Date;

public class Message {
	
	private String subject;
	private String body;
	private int sender;
	private int recipient;
	private Date dateSent;
	
	public Message() {
		
	}
	
	// setters
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public void setSender(int sender) {
		this.sender = sender;
	}
	public void setRecipient(int recipient) {
		this.recipient = recipient;
	}
	public void setDate(Date date) {
		this.dateSent = date;
	}
	
	// getters
	public String getSubject() {
		return this.subject;
	}
	public String getBody() {
		return this.body;
	}
	public int getSender() {
		return this.sender;
	}
	public int getRecipient() {
		return this.recipient;
	}
	public Date getDate() {
		return this.dateSent;
	}
	
}
