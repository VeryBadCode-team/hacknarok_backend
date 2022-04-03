package com.tarbus.payload.response;

import com.tarbus.payload.dto.ChatCommentDto;
import com.tarbus.payload.dto.EventCategoryDto;
import com.tarbus.payload.dto.EventUserDto;
import com.tarbus.payload.entities.mongo.EventAuthorType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class JoinMeEventDetailsResponse {
  protected String id;
  protected EventUserDto author;
  protected String description;
  protected String backgroundId;
  protected EventAuthorType authorType;
  private EventCategoryDto category;
  private List<ChatCommentDto> comments;
  protected double lat;
  protected double lng;
  protected boolean isHighlighted;
  private int maxUsers;
  private int userLeft;
  protected List<EventUserDto> users;
  protected Date createdAt;
  protected Date updatedAt;
}
