package com.tarbus.payload.response;

import com.tarbus.payload.dto.EventCategoryDto;
import com.tarbus.payload.dto.EventUserDto;
import com.tarbus.payload.entities.mongo.EventAuthorType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class JoinMeEventResponse {
  protected String id;
  protected EventUserDto author;
  protected String description;
  protected EventAuthorType authorType;
  protected boolean isHighlighted;
  private EventCategoryDto category;
  protected double lat;
  protected double lng;
  private int maxUsers;
  private int userLeft;
  protected Date createdAt;
  protected Date updatedAt;
}
