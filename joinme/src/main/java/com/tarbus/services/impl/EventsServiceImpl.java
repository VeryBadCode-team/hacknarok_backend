package com.tarbus.services.impl;

import com.tarbus.exceptions.BadRequestException;
import com.tarbus.payload.entities.mongo.JoinMeEvent;
import com.tarbus.payload.entities.mongo.UserJoin;
import com.tarbus.payload.entity.User;
import com.tarbus.payload.mappers.JoinMeEventMapper;
import com.tarbus.payload.request.CreateCompanyJoinMeEventRequest;
import com.tarbus.payload.request.CreateJoinMeEventRequest;
import com.tarbus.payload.request.CreateUserJoinMeEventRequest;
import com.tarbus.repositories.mongo.EventsRepository;
import com.tarbus.services.AuthService;
import com.tarbus.services.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventsServiceImpl implements EventsService {

  private final EventsRepository eventsRepository;
  private final JoinMeEventMapper joinMeEventMapper;
  private final AuthService authService;

  @Autowired
  public EventsServiceImpl(EventsRepository eventsRepository, JoinMeEventMapper joinMeEventMapper, AuthService authService) {
    this.eventsRepository = eventsRepository;
    this.joinMeEventMapper = joinMeEventMapper;
    this.authService = authService;
  }

  @Override
  public List<JoinMeEvent> getAll() {
    return eventsRepository.findAll();
  }

  @Override
  public JoinMeEvent create(CreateJoinMeEventRequest request) {
    if( request instanceof CreateCompanyJoinMeEventRequest) {
      return eventsRepository.save(joinMeEventMapper.mapToEntity((CreateCompanyJoinMeEventRequest) request));
    } else if( request instanceof CreateUserJoinMeEventRequest) {
      return eventsRepository.save(joinMeEventMapper.mapToEntity((CreateUserJoinMeEventRequest) request));
    }
    throw new BadRequestException();
  }

  @Override
  public JoinMeEvent getById(String id) {
    Optional<JoinMeEvent> optionalEvent = eventsRepository.findById(id);
    if(optionalEvent.isPresent()) {
      return optionalEvent.get();
    }
    throw new BadRequestException();
  }

  @Override
  public void join(String id) {
    Optional<JoinMeEvent> joinMeEventOptional = eventsRepository.findById(id);
    if(joinMeEventOptional.isPresent()) {
      User currentUser = authService.getSessionUser();
      JoinMeEvent joinMeEvent = joinMeEventOptional.get();
      UserJoin userJoin = new UserJoin(currentUser.getId(), new Date());
      Set<UserJoin> eventUsers = joinMeEvent.getUsers() != null ? new HashSet<>(joinMeEvent.getUsers()) : new HashSet<>();
      eventUsers.add(userJoin);
      joinMeEvent.setUsers(new ArrayList<>(eventUsers));
      eventsRepository.save(joinMeEvent);
    }
  }
}
