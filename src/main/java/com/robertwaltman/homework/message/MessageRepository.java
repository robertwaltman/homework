package com.robertwaltman.homework.message;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> 
{
	public Message findById(String id);
	public List<Message> findByMessageType(String messageType);
	//public List<Message> findByMessageTypeAndGreaterThanTimeStamp(List<String> messageType, Long timeStamp);
	
}
