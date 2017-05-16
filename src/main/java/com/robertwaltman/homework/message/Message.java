package com.robertwaltman.homework.message;

import org.springframework.data.annotation.Id;

public class Message {
	
	@Id
	protected String id;
	
	protected String messageType;
	protected String message;
	protected Long timeStamp;
	
	public Message(){}
	public Message(String theMessageType, String theMessage, Long theTimeStamp)
	{
		messageType = theMessageType;
		message = theMessage;
		timeStamp = theTimeStamp;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	@Override
	public String toString()
	{
		return String.format("Message[id(%s), messageType(%s), message(%s), timeStamp(%d)]", 
				id, messageType, message, timeStamp);
	}

}
