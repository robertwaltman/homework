package com.robertwaltman.homework.message;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MessageSearchRepository 
{
	@Autowired
	MongoTemplate theTemplate;
	
	
	public Collection<Message> searchMessagesByType(List<String> theMessageTypes)
	{
		Collection<Message> theResult;
		
		Criteria theCriteria = Criteria.where("messageType").in(theMessageTypes);

		theResult = theTemplate.find(Query.query(theCriteria), Message.class);
		
		return theResult;
	}
}
