package com.robertwaltman.homework.message;

import java.util.List;
import java.util.Map;

public class QueryResponse 
{

	protected List<Message> messages;
	protected Map<String, Long> messageTypeMap;
	
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	public Map<String, Long> getMessageTypeCounts() {
		return messageTypeMap;
	}
	public void setMessageTypeCounts(Map<String, Long> messageTypeMap) {
		this.messageTypeMap = messageTypeMap;
	}

	@Override
	public String toString()
	{
		return String.format("QueryResponse[ Messages (%s),Message Type Counts(%s)]", messages, messageTypeMap);

	}
}
