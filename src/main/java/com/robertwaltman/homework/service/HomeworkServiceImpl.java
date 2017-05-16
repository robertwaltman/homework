package com.robertwaltman.homework.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Component;

import com.robertwaltman.homework.message.Message;
import com.robertwaltman.homework.message.MessageRepository;
import com.robertwaltman.homework.message.MessageSearchRepository;
import com.robertwaltman.homework.subscription.Subscription;
import com.robertwaltman.homework.subscription.SubscriptionRepository;

@Component
public class HomeworkServiceImpl implements HomeworkService
{
	
	@Autowired
	MessageRepository myMessageRepository;

	@Autowired
	SubscriptionRepository mySubRepository;

	@Autowired
	MessageSearchRepository mySearchRepository;

	
	private static final Logger logger = Logger.getLogger(HomeworkServiceImpl.class.getName());
	

	/**
	 * This service method returns the messages for the given subscription.  If the subscription
	 * is not found an exception is thrown.
	 * @param theSubscriptionId
	 * @return
	 */
	public List<Message> findMessages(String theSubscriptionId)
	{
		List<Message> theResult = new ArrayList<Message>();
		logger.log(Level.FINE,"Getting messages for subscription (" + theSubscriptionId + ")");
		
		Subscription theSubscription = mySubRepository.findById(theSubscriptionId);
		if (theSubscription != null)
		{
			logger.log(Level.FINE,"Found subscription (" + theSubscription + ")");
			Collection<Message> theMessages = mySearchRepository.searchMessagesByType(theSubscription.getMessageTypes());
			theResult.addAll(theMessages);			
		}
		else
		{
			logger.log(Level.FINE,"subscription not found (" + theSubscriptionId + ")");
			throw new DataRetrievalFailureException("Unable to retrieve subscription (" +theSubscriptionId + ")");
		}
		
		return theResult;
	}
	

	/**
	 * This Service method adds a new subscription to the datastore. 
	 * @param theSubscription
	 * @return
	 */
	public Subscription addSubscription(Subscription theSubscription)
	{
		Subscription theResult = null;
		logger.log(Level.FINE,"Creating subscription (" + theSubscription + ")");
		
		theResult = mySubRepository.save(theSubscription);
		
		return theResult;
	}
	
	
	/**
	 * This service method updates an existing subscription.  If the subscription id is not valid then an error is thrown.  
	 * 
	 * @param theSubscription
	 * @return
	 */
	public Subscription updateSubscription(Subscription theSubscription)
	{
		Subscription theResult = null;
		logger.log(Level.FINE,"Creating subscription (" + theSubscription + ") ");
		
		if (theSubscription == null || theSubscription.getId() == null)
		{
			throw new UnsupportedOperationException("Subscription id was null on update (" + theSubscription + ").");
		}
		
		theResult = mySubRepository.save(theSubscription);
		
		return theResult;
	}
	
	/**
	 * Add a new message to the datastore
	 * @param theMessage
	 * @return
	 */
	public Message addMessage(Message theMessage)
	{
		Message theResult = null;

		if (theMessage.getMessageType() == null || theMessage.getMessageType().isEmpty())
		{
			throw new UnsupportedOperationException("MessageType was null on create (" + theMessage + ").");
		}
		Long theTimeStamp = new Date().getTime();
		theMessage.setTimeStamp(theTimeStamp);
		logger.log(Level.FINE,"Creating message (" + theMessage + ")");
		
		theResult = myMessageRepository.save(theMessage);
		
		return theResult;
	}

	
	
}
