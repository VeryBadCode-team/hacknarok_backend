package com.tarbus.payload.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.*;
import com.tarbus.payload.entities.mongo.EventAuthorType;
import com.tarbus.payload.request.CreateCompanyJoinMeEventRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@JsonTypeInfo(use = Id.NAME, property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = CreateUserJoinMeEventRequest.class, name = "user"),
  @JsonSubTypes.Type(value = CreateCompanyJoinMeEventRequest.class, name = "company"),
})

@Getter
@Setter
@NoArgsConstructor
abstract public class CreateJoinMeEventRequest {
  protected EventAuthorType type;
  protected double lat;
  protected double lng;
  protected String authorId;
  protected String description;
  protected String eventDescription;
  protected Date eventTime;
  protected int maxUsers;
}
