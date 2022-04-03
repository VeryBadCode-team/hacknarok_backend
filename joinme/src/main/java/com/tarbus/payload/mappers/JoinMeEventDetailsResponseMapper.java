package com.tarbus.payload.mappers;

import com.tarbus.payload.dto.ChatCommentDto;
import com.tarbus.payload.dto.EventUserDto;
import com.tarbus.payload.dto.HighlightObject;
import com.tarbus.payload.entities.jpa.EventCategory;
import com.tarbus.payload.entities.mongo.EventAuthorType;
import com.tarbus.payload.entities.mongo.JoinMeEvent;
import com.tarbus.payload.entities.mongo.UserJoin;
import com.tarbus.payload.entities.mongo.event_types.CompanyJoinMeEvent;
import com.tarbus.payload.entity.User;
import com.tarbus.payload.entities.jpa.ChatComment;
import com.tarbus.payload.response.CompanyJoinMeEventDetailsResponse;
import com.tarbus.payload.response.JoinMeEventDetailsResponse;
import com.tarbus.payload.response.UserJoinMeEventDetailsResponse;
import com.tarbus.services.ChatCommentService;
import com.tarbus.services.EventCategoryService;
import com.tarbus.services.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JoinMeEventDetailsResponseMapper {
  private final UserDataService userDataService;
  private final EventUserMapper eventUserMapper;
  private final EventCategoryService eventCategoryService;
  private final EventCategoryDtoMapper eventCategoryDtoMapper;
  private final ChatCommentService chatCommentService;
  private final ChatCommentDtoMapper chatCommentDtoMapper;

  @Autowired
  public JoinMeEventDetailsResponseMapper(UserDataService userDataService, EventUserMapper eventUserMapper, EventCategoryService eventCategoryService, EventCategoryDtoMapper eventCategoryDtoMapper, ChatCommentService chatCommentService, ChatCommentDtoMapper chatCommentDtoMapper) {
    this.userDataService = userDataService;
    this.eventUserMapper = eventUserMapper;
    this.eventCategoryService = eventCategoryService;
    this.eventCategoryDtoMapper = eventCategoryDtoMapper;
    this.chatCommentService = chatCommentService;
    this.chatCommentDtoMapper = chatCommentDtoMapper;
  }


  public JoinMeEventDetailsResponse mapToEntity(JoinMeEvent joinMeEvent) {
    JoinMeEventDetailsResponse response;
    if (joinMeEvent.getAuthorType() == EventAuthorType.company) {
      response = new CompanyJoinMeEventDetailsResponse();
      ((CompanyJoinMeEventDetailsResponse) response).setEventName(((CompanyJoinMeEvent) joinMeEvent).getEventName());
      ((CompanyJoinMeEventDetailsResponse) response).setImageId(((CompanyJoinMeEvent) joinMeEvent).getImageId());
      setDefaults(response, joinMeEvent);
    } else {
      response = new UserJoinMeEventDetailsResponse();
      setDefaults(response, joinMeEvent);
    }
    return response;
  }

  public void setDefaults(JoinMeEventDetailsResponse response, JoinMeEvent joinMeEvent) {
    response.setId(joinMeEvent.getId());
    User user = userDataService.getUserById(joinMeEvent.getAuthorId());
    response.setAuthor(eventUserMapper.mapToEntity(user));
    response.setAuthorType(joinMeEvent.getAuthorType());
    response.setLat(joinMeEvent.getLat());
    response.setLng(joinMeEvent.getLng());
    response.setMaxUsers(joinMeEvent.getMaxUsers());
    response.setDescription(joinMeEvent.getDescription());


    List<EventUserDto> users = new ArrayList<>();
    for (UserJoin userJoin : joinMeEvent.getUsers()) {
      users.add(eventUserMapper.mapToEntity(userDataService.getUserById(userJoin.getUserId())));
    }

    boolean highlighted = false;
    Date now = new Date();
    if( joinMeEvent.getHighlights() != null ) {
      for (HighlightObject highlightEndDate : joinMeEvent.getHighlights()) {
        if (highlightEndDate.getDateEnd().before(now)) {
          highlighted = true;
          break;
        }
      }
    }
    response.setHighlighted(highlighted);
    response.setUsers(users);
    response.setUserLeft(joinMeEvent.getMaxUsers() - users.size());
    response.setUpdatedAt(joinMeEvent.getUpdatedAt());
    response.setCreatedAt(joinMeEvent.getCreatedAt());

    List<ChatComment> chatComments = chatCommentService.getAllChatCommentsForEvent(joinMeEvent.getId());
    List<ChatCommentDto> chatCommentDtos = new ArrayList<>();
    for( ChatComment chatComment : chatComments) {
      chatCommentDtos.add(chatCommentDtoMapper.mapToEntity(chatComment));
    }
    response.setBackgroundId(joinMeEvent.getBackgroundId());
    response.setComments(chatCommentDtos);
    EventCategory eventCategory = eventCategoryService.getEventCategoryById(joinMeEvent.getCategoryId());
    response.setCategory(eventCategoryDtoMapper.mapToEntity(eventCategory));
  }
}
