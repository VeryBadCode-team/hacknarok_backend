package com.tarbus.payload.mappers;

import com.tarbus.payload.entities.mongo.EventAuthorType;
import com.tarbus.payload.entities.mongo.JoinMeEvent;
import com.tarbus.payload.entities.mongo.event_types.CompanyJoinMeEvent;
import com.tarbus.payload.entities.mongo.event_types.UserJoinMeEvent;
import com.tarbus.payload.entity.User;
import com.tarbus.payload.request.CreateCompanyJoinMeEventRequest;
import com.tarbus.payload.request.CreateUserJoinMeEventRequest;
import com.tarbus.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Component
public class JoinMeEventMapper {
  private final AuthService authService;

  @Autowired
  public JoinMeEventMapper(AuthService authService) {
    this.authService = authService;
  }

  public JoinMeEvent mapToEntity(CreateCompanyJoinMeEventRequest request) {
    User currentUser = authService.getSessionUser();

    CompanyJoinMeEvent event = new CompanyJoinMeEvent();
    event.setId(String.valueOf(UUID.randomUUID()));
    event.setAuthorId(currentUser.getId());
    event.setUsers(new ArrayList<>());
    event.setDescription(request.getDescription());
    event.setAuthorType(EventAuthorType.company);
    event.setMaxUsers(request.getMaxUsers());
    event.setEventName(request.getEventName());
    event.setCreatedAt(new Date());
    event.setUpdatedAt(new Date());
    event.setLat(request.getLat());
    event.setLng(request.getLng());
    return event;
  }

  public JoinMeEvent mapToEntity(CreateUserJoinMeEventRequest request) {
    User currentUser = authService.getSessionUser();

    UserJoinMeEvent event = new UserJoinMeEvent();
    event.setId(String.valueOf(UUID.randomUUID()));
    event.setCategoryId(request.getCategoryId());
    event.setDescription(request.getDescription());
    event.setAuthorId(currentUser.getId());
    event.setUsers(new ArrayList<>());
    event.setAuthorType(EventAuthorType.user);
    event.setMaxUsers(request.getMaxUsers());
    event.setCreatedAt(new Date());
    event.setUpdatedAt(new Date());
    event.setLat(request.getLat());
    event.setLng(request.getLng());
    return event;
  }
}
