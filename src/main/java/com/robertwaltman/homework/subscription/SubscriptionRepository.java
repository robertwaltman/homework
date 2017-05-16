package com.robertwaltman.homework.subscription;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubscriptionRepository extends MongoRepository<Subscription, String> 
{
	Subscription findById(String id);
}
