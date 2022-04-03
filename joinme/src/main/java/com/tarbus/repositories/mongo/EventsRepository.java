package com.tarbus.repositories.mongo;

import com.tarbus.payload.entities.mongo.JoinMeEvent;
import  org.springframework.data.mongodb.repository.MongoRepository;

public interface EventsRepository extends MongoRepository<JoinMeEvent, String> {

}
