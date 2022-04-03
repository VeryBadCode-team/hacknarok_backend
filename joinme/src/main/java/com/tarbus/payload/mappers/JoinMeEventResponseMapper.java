package com.tarbus.payload.mappers;

import com.tarbus.payload.dto.HighlightObject;
import com.tarbus.payload.entities.jpa.EventCategory;
import com.tarbus.payload.entities.mongo.EventAuthorType;
import com.tarbus.payload.entities.mongo.JoinMeEvent;
import com.tarbus.payload.entities.mongo.event_types.CompanyJoinMeEvent;
import com.tarbus.payload.entities.mongo.event_types.UserJoinMeEvent;
import com.tarbus.payload.mappers.EventCategoryDtoMapper;
import com.tarbus.payload.mappers.EventUserMapper;
import com.tarbus.payload.entity.User;
import com.tarbus.payload.response.CompanyJoinMeEventResponse;
import com.tarbus.payload.response.JoinMeEventResponse;
import com.tarbus.payload.response.UserJoinMeEventResponse;
import com.tarbus.services.EventCategoryService;
import com.tarbus.services.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JoinMeEventResponseMapper {

  private final EventCategoryService eventCategoryService;
  private final EventCategoryDtoMapper eventCategoryDtoMapper;
  private final UserDataService userDataService;
  private final EventUserMapper eventUserMapper;

  @Autowired
  public JoinMeEventResponseMapper(EventCategoryService eventCategoryService, EventCategoryDtoMapper eventCategoryDtoMapper, UserDataService userDataService, EventUserMapper eventUserMapper) {
    this.eventCategoryService = eventCategoryService;
    this.eventCategoryDtoMapper = eventCategoryDtoMapper;
    this.userDataService = userDataService;
    this.eventUserMapper = eventUserMapper;
  }



  public JoinMeEventResponse mapToEntity(JoinMeEvent joinMeEvent) {
    JoinMeEventResponse response;
    System.out.println(joinMeEvent);
    if (joinMeEvent.getAuthorType() == EventAuthorType.company) {
      response = new CompanyJoinMeEventResponse();
      CompanyJoinMeEvent companyJoinMeEvent = (CompanyJoinMeEvent) joinMeEvent;
      ((CompanyJoinMeEventResponse) response).setEventName((companyJoinMeEvent).getEventName());
      ((CompanyJoinMeEventResponse) response).setImageId(((CompanyJoinMeEvent) joinMeEvent).getImageId());
    } else {
      response = new UserJoinMeEventResponse();
    }
    setDefaults(response, joinMeEvent);

    return response;
  }

  public void setDefaults(JoinMeEventResponse response, JoinMeEvent joinMeEvent) {
    response.setId(joinMeEvent.getId());
    User user = userDataService.getUserById(joinMeEvent.getAuthorId());
    response.setAuthor(eventUserMapper.mapToEntity(user));
    response.setAuthorType(joinMeEvent.getAuthorType());
    response.setLat(joinMeEvent.getLat());
    response.setLng(joinMeEvent.getLng());

    EventCategory eventCategory = eventCategoryService.getEventCategoryById(joinMeEvent.getCategoryId());
    response.setCategory(eventCategoryDtoMapper.mapToEntity(eventCategory));

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
    response.setDescription(joinMeEvent.getDescription());
    response.setMaxUsers(joinMeEvent.getMaxUsers());
    response.setUserLeft(joinMeEvent.getMaxUsers() - joinMeEvent.getUsers().size());
    response.setUpdatedAt(joinMeEvent.getUpdatedAt());
    response.setCreatedAt(joinMeEvent.getCreatedAt());
  }
}
