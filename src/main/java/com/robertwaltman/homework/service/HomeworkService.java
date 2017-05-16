package com.robertwaltman.homework.service;

import java.util.List;

import com.robertwaltman.homework.message.Message;
import com.robertwaltman.homework.subscription.Subscription;

public interface HomeworkService 
{
	Message addMessage(Message theMessage);
	List<Message> findMessages(String theSubscriptionId);
	Subscription addSubscription(Subscription theSubscription);
	Subscription updateSubscription(Subscription theSubscription);
}
