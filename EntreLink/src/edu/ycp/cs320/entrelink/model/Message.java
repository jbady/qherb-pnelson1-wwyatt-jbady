package edu.ycp.cs320.entrelink.model;

import java.util.Date;

public class Message {

	private int msgId;
	private int sender;
	private int recipient;
	private String dateSent;
	private String subject;
	private String body;
	private int read; //1=read; 0=unread
	
	// some other variables
	private String senderName;
	private String recipientName;
	
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
	public void setDate(String date) {
		this.dateSent = date;
	}
	public void setRead(int read) {
		this.read = read;
	}
	public void setMessageId(int id) {
		this.msgId = id;
	}
	public void setRecipientName(String name) {
		recipientName = name;
	}
	public void setSenderName(String name) {
		senderName = name;
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
	public String getDate() {
		return this.dateSent;
	}
	
	public int getRead() {
		return this.read;
	}
	public int getMessageId() {
		return this.msgId;
	}
	public String getRecipientName() {
		return this.recipientName;
	}
	public String getSenderName() {
		return this.senderName;
	}
	
}
