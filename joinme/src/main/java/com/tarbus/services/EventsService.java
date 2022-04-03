package com.tarbus.services;

import com.tarbus.payload.entities.mongo.JoinMeEvent;
import com.tarbus.payload.request.CreateJoinMeEventRequest;

import java.util.List;

public interface EventsService {
  List<JoinMeEvent> getAll();
  JoinMeEvent create(CreateJoinMeEventRequest request);
  JoinMeEvent getById(String id);
  void join(String id);
}
