package com.tarbus.controllers;

import com.tarbus.exceptions.RequestException;
import com.tarbus.payload.entities.mongo.JoinMeEvent;
import com.tarbus.payload.mappers.JoinMeEventDetailsResponseMapper;
import com.tarbus.payload.mappers.JoinMeEventResponseMapper;
import com.tarbus.payload.request.CreateChatCommentRequest;
import com.tarbus.payload.request.CreateJoinMeEventRequest;
import com.tarbus.payload.response.JoinMeEventResponse;
import com.tarbus.services.ChatCommentService;
import com.tarbus.services.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/joinme")
public class EventsController {

  private final EventsService eventsService;
  private final JoinMeEventResponseMapper joinMeEventResponseMapper;
  private final JoinMeEventDetailsResponseMapper joinMeEventDetailsResponseMapper;
  private final ChatCommentService chatCommentService;

  @Autowired
  public EventsController(EventsService eventsService, JoinMeEventResponseMapper joinMeEventResponseMapper, JoinMeEventDetailsResponseMapper joinMeEventDetailsResponseMapper, ChatCommentService chatCommentService) {
    this.eventsService = eventsService;
    this.joinMeEventResponseMapper = joinMeEventResponseMapper;
    this.joinMeEventDetailsResponseMapper = joinMeEventDetailsResponseMapper;
    this.chatCommentService = chatCommentService;
  }

  @PreAuthorize("hasRole('USER')")
  @GetMapping({"/events"})
  public ResponseEntity<?> getAll() {
    try {
      List<JoinMeEvent> joinMeEvents = eventsService.getAll();
      List<JoinMeEventResponse> response = new ArrayList<>();
      for (JoinMeEvent event : joinMeEvents) {
        response.add(joinMeEventResponseMapper.mapToEntity(event));
      }
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (RequestException e) {
      return e.toResponseEntity();
    }
  }

  @PreAuthorize("hasRole('USER')")
  @GetMapping({"/event/{id}"})
  public ResponseEntity<?> getEventById(@PathVariable("id") String id) {
    try {
      JoinMeEvent joinMeEvent = eventsService.getById(id);
      return new ResponseEntity<>(joinMeEventDetailsResponseMapper.mapToEntity(joinMeEvent), HttpStatus.OK);
    } catch (RequestException e) {
      return e.toResponseEntity();
    }
  }

  @PreAuthorize("hasRole('USER')")
  @PutMapping({"/events"})
  public ResponseEntity<?> createEvent(@RequestBody() CreateJoinMeEventRequest createCompanyRequest) {
    try {
      JoinMeEvent response = eventsService.create(createCompanyRequest);
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (RequestException e) {
      return e.toResponseEntity();
    }
  }

  @PreAuthorize("hasRole('USER')")
  @PostMapping({"/event/{id}/join"})
  public ResponseEntity<?> joinEvent(@PathVariable("id") String id) {
    try {
      eventsService.join(id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (RequestException e) {
      return e.toResponseEntity();
    }
  }

  @PreAuthorize("hasRole('USER')")
  @PostMapping({"/event/comments"})
  public ResponseEntity<?> createCommentForRoom( @RequestBody() CreateChatCommentRequest createCompanyRequest) {
    try {
      System.out.println(createCompanyRequest);
      chatCommentService.createChatCommentForEvent(createCompanyRequest.getId(), createCompanyRequest.getMessage());
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (RequestException e) {
      return e.toResponseEntity();
    }
  }
}
