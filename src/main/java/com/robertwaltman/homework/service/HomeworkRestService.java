package com.robertwaltman.homework.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.robertwaltman.homework.message.Message;
import com.robertwaltman.homework.message.QueryResponse;
import com.robertwaltman.homework.subscription.Subscription;

@RestController
public class HomeworkRestService 
{
	@Autowired
	HomeworkService theService;
	
	private static final Logger logger = Logger.getLogger(HomeworkRestService.class.getName());
	
	@RequestMapping(value = "/subscriptions/{id}", method=RequestMethod.GET)
	public @ResponseBody QueryResponse findMessages(@PathVariable("id") String theSubscriptionId)
	{
		QueryResponse theResult = new QueryResponse();
		List<Message> theMessages = theService.findMessages(theSubscriptionId);
		
		// Calculate the message type counts
		Map<String, Long> theCountMap = new HashMap<String, Long>();
		for (Message aMessage : theMessages)
		{
			countMessageType(aMessage, theCountMap);
		}
		theResult.setMessages(theMessages);
		theResult.setMessageTypeCounts(theCountMap);
		
		return theResult;
	}

	private void countMessageType(Message theMessage, Map<String, Long> theCountMap) 
	{
		Long theCount = theCountMap.get(theMessage.getMessageType());
		if (theCount == null)
		{
			theCount = new Long(0);
		}
		theCount++;
		theCountMap.put(theMessage.getMessageType(), theCount);
	}


	@RequestMapping(value = "/subscriptions", method=RequestMethod.POST)
	public @ResponseBody Subscription addSubscription(@RequestBody Subscription theSubscription)
	{
		return theService.addSubscription(theSubscription);
	}
	
	/**
	 * Check that the path parameter, subscriptionId matches what is in the Subscription parameter.  This enforces
	 * a query before update and maintains the url schema.
	 * @param theSubscriptionId
	 * @param theSubscription
	 * @return
	 */
	@RequestMapping(value = "/subscriptions/{id}", method=RequestMethod.PUT)
	public @ResponseBody Subscription updateSubscription(@PathVariable("id") String theSubscriptionId, @RequestBody Subscription theSubscription)
	{
		if (theSubscriptionId == null || theSubscription == null || !theSubscriptionId.equals(theSubscription.getId()))
		{
			logger.log(Level.SEVERE, "Subscription Id is null or invalid");
			throw new UnsupportedOperationException("Subscription id is null or invalid (" + theSubscriptionId + ") Subscription (" + theSubscription + ".");
		}
		return theService.updateSubscription(theSubscription);
	}
	
	@RequestMapping(value = "/messages", method=RequestMethod.POST)
	public @ResponseBody Message addMessage(@RequestBody Message theMessage)
	{
		return theService.addMessage(theMessage);
	}
	
}
