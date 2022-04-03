package com.tarbus.payload.entities.mongo;

import com.tarbus.payload.dto.HighlightObject;
import com.tarbus.payload.entities.mongo.EventAuthorType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "join_me_events")
public abstract class JoinMeEvent {
  @Id
  protected String id;
  protected String authorId;
  protected String backgroundId;
  protected String description;
  protected EventAuthorType authorType;
  protected List<HighlightObject> highlights;
  private String categoryId;
  protected double lat;
  protected double lng;
  private int maxUsers;
  protected List<UserJoin> users;
  protected Date createdAt;
  protected Date updatedAt;
}
