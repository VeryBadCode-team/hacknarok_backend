package com.tarbus.payload.entities.jpa;

import com.tarbus.payload.entity.FileObject;
import com.tarbus.payload.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "chat_comments")
@Getter
@Setter
@NoArgsConstructor
public class ChatComment {
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
  private String id;

  private String eventId;

  private Date createdAt;

  private String comment;

  @OneToOne()
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;
}
