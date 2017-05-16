package com.robertwaltman.homework.subscription;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Subscription 
{
	@Id
	String id;
	List<String> messageTypes;
	
	public Subscription() {}
	public Subscription(List<String> theMessageTypes)
	{
		messageTypes = theMessageTypes;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<String> getMessageTypes() {
		return messageTypes;
	}
	public void setMessageTypes(List<String> messageTypes) {
		this.messageTypes = messageTypes;
	}

	@Override
	public String toString()
	{
		return String.format("Subscription[ messageTypes(%s)]", (messageTypes == null ? "null" : messageTypes.toString()));
		
	}
}
