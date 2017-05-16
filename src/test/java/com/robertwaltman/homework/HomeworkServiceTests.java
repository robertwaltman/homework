package com.robertwaltman.homework;

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
import com.robertwaltman.homework.service.HomeworkServiceImpl;
import com.robertwaltman.homework.subscription.Subscription;
import com.robertwaltman.homework.subscription.SubscriptionRepository;



@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class HomeworkServiceTests {

	@Mock
	SubscriptionRepository theSubRepo;
	
	@Mock  
	MessageSearchRepository theSearchRepository;
	
	@Mock  
	MessageRepository theMessageRepo;
	
	
	@InjectMocks
	HomeworkServiceImpl theService = new HomeworkServiceImpl();

	List<Message> theTestCollection = new ArrayList<Message>();
	Subscription theTestSubscription = null;
	List<String> theTypeList = new ArrayList<String>();
	Message theTestMessage;
	Message theRetrievedMessage;

	@Before
	public void setUp() throws Exception 
	{
		theTestMessage =  new Message("foo","fubar",1L);
		theRetrievedMessage = new Message("foo","fubar",1L);
		theRetrievedMessage.setId("2");
		theTestCollection.add(theRetrievedMessage);
		
		theTypeList.add("foo");
		theTypeList.add("bar");
		theTestSubscription = new Subscription(theTypeList);
		theTestSubscription.setId("100");
		
	}

	@After
	public void tearDown() throws Exception 
	{
	}

	
	@SuppressWarnings("unchecked")
	@Test
	public void querySubscriptionTest()
	{
		when(theSubRepo.findById(any())).thenReturn(theTestSubscription);
		when(theSearchRepository.searchMessagesByType(any(List.class))).thenReturn(theTestCollection);
		List<Message> theResult = theService.findMessages("100");
		assertNotNull(theResult);
		assertEquals(theResult.size(), 1l);
		for (Message aMessage: theResult)
		{
			assertEquals(aMessage.getMessageType(), "foo");
		}
		
	}

	@Test
	public void addSubscriptionTest() 
	{
		when(theSubRepo.save(any(Subscription.class))).thenReturn(theTestSubscription);
		Subscription theData = new Subscription(theTypeList);
		Subscription theResult = theService.addSubscription(theData);

		assertNotNull(theResult);
		assertNotNull(theResult.getId());
	}
	
	@Test
	public void updateSubscriptionTest()
	{
		when(theSubRepo.save(any(Subscription.class))).thenReturn(theTestSubscription);
		Subscription theData = new Subscription(theTypeList);
		theData.setId("100");
		Subscription theResult = theService.updateSubscription(theData);

		assertNotNull(theResult);
		assertNotNull(theResult.getId());
		assertEquals(theResult.getId(), "100");
	}
	@Test
	public void updateSubscriptionTestFail()
	{
		when(theSubRepo.save(any(Subscription.class))).thenReturn(theTestSubscription);
		Subscription theData = new Subscription(theTypeList);
		theData.setId(null);
		
		try
		{
			Subscription theResult = theService.updateSubscription(theData);
	
			assertNotNull(theResult);
			assertNotNull(theResult.getId());
			assertEquals(theResult.getId(), "100");
			fail();
		}
		catch (UnsupportedOperationException e)
		{
			
		}
	}
	
	@Test
	public void addMessageTest()
	{
		when(theMessageRepo.save(any(Message.class))).thenReturn(theRetrievedMessage);
		Message theResult = theService.addMessage(theTestMessage);
		assertNotNull(theResult);
		assertNotNull(theResult.getId());
		assertEquals(theResult.getId(), "2");

	}
}
