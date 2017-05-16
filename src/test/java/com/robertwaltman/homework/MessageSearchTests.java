package com.robertwaltman.homework;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.robertwaltman.homework.message.Message;
import com.robertwaltman.homework.message.MessageSearchRepository;


@RunWith(MockitoJUnitRunner.class)
public class MessageSearchTests {
	
	@Mock
	MongoTemplate theTemplate;

	@InjectMocks
	MessageSearchRepository theSearchRepository = new MessageSearchRepository();

	List<Message> theTestCollection = new ArrayList<Message>();

	@Before
	public void setUp() throws Exception 
	{
		Message testMessage = new Message("foo","fubar",1L);
		theTestCollection.add(testMessage);
		
	}

	@After
	public void tearDown() throws Exception 
	{
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testSearchMessagesByType() 
	{
		
		List<String> testList = new ArrayList<String>();
		testList.add("foo");
		testList.add("bar");
		
		if (theTemplate == null) System.out.println("Template is null");
		
		when(theTemplate.find(Mockito.any(),Mockito.any(Class.class))).thenReturn(theTestCollection);
		
		Collection<Message> theResult = theSearchRepository.searchMessagesByType(testList);
		assertNotNull(theResult);
		assertEquals(theResult.size(), 1l);
		for (Message aMessage: theResult)
		{
			assertEquals(aMessage.getMessageType(), "foo");
		}
	}

}
