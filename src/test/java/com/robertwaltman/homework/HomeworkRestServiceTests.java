package com.robertwaltman.homework;

import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.robertwaltman.homework.message.Message;
import com.robertwaltman.homework.message.MessageRepository;
import com.robertwaltman.homework.message.MessageSearchRepository;
import com.robertwaltman.homework.message.QueryResponse;
import com.robertwaltman.homework.service.HomeworkRestService;
import com.robertwaltman.homework.service.HomeworkService;
import com.robertwaltman.homework.service.HomeworkServiceImpl;
import com.robertwaltman.homework.subscription.Subscription;
import com.robertwaltman.homework.subscription.SubscriptionRepository;

@RunWith(MockitoJUnitRunner.class)
public class HomeworkRestServiceTests {
	
	@Mock
	HomeworkService theHomeworkService;
	
	@InjectMocks
	HomeworkRestService theService = new HomeworkRestService();

	List<Message> theTestCollection = new ArrayList<Message>();
	Subscription theTestSubscription = null;
	List<String> theTypeList = new ArrayList<String>();
	List<Message> theMessageList;
	
	Message theResponseMessage = new Message("foo","fubar",100L);

	@Before
	public void setUp() throws Exception 
	{
		theResponseMessage.setId("100");
		Message theTestMessage1 =  new Message("foo","fubar",1L);
		theTestMessage1.setId("1");
		Message theTestMessage2 = new Message("bar","fubar",1L);
		theTestMessage2.setId("2");
		Message theTestMessage3 =  new Message("foo","fubar2",2L);
		theTestMessage3.setId("3");
		
		theMessageList = new ArrayList<Message>();
		theMessageList.add(theTestMessage1);
		theMessageList.add(theTestMessage2);
		theMessageList.add(theTestMessage3);
		
		theTypeList.add("foo");
		theTypeList.add("bar");
		theTestSubscription = new Subscription(theTypeList);
		theTestSubscription.setId("100");
		

		
	}


	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void findMessagesTest() {
		when(theHomeworkService.findMessages(any(String.class))).thenReturn(theMessageList);
		QueryResponse theResult = theService.findMessages("100");
		
		assertNotNull(theResult);
		assertNotNull(theResult.getMessages());
		assertNotNull(theResult.getMessageTypeCounts());
		assertEquals((Long)theResult.getMessageTypeCounts().get("foo"),new Long(2));

	}

	@Test
	public void addSubscriptionTest() {
		when(theHomeworkService.addSubscription(any(Subscription.class))).thenReturn(theTestSubscription);
		List<String> theMsgTypeList = new ArrayList<String>();
		theMsgTypeList.add("foo");
		Subscription theSubscription = new Subscription(theMsgTypeList);
		
		Subscription theResult = theService.addSubscription(theSubscription);
		assertNotNull(theResult);
		assertNotNull(theResult.getId());

	}
	@Test
	public void updateSubscriptionTest() {
		when(theHomeworkService.updateSubscription(any(Subscription.class))).thenReturn(theTestSubscription);
		
		
		List<String> theMsgTypeList = new ArrayList<String>();
		theMsgTypeList.add("foo");
		Subscription theSubscription = new Subscription(theMsgTypeList);
		theSubscription.setId("1");
		
		Subscription theResult = theService.updateSubscription("1", theSubscription);
		assertNotNull(theResult);
		assertNotNull(theResult.getId());
	
		try
		{
			theService.updateSubscription("3", theSubscription);
			fail("Exception should be thrown for mismatched id");
		}
		catch (UnsupportedOperationException e)
		{
			
		}
		try
		{
			theService.updateSubscription(null, theSubscription);
			fail("Exception should be thrown for null id");
		}
		catch (UnsupportedOperationException e)
		{
			
		}
	}

	@Test
	public void addMessageTest() {
		when(theHomeworkService.addMessage(any(Message.class))).thenReturn(theResponseMessage);
		
		Message theTestMessage =  new Message("foo","fubar",1L);
		Message theResult = theService.addMessage(theTestMessage);
		
		assertNotNull(theResult);
		assertNotNull(theResult.getId());

	}


}
