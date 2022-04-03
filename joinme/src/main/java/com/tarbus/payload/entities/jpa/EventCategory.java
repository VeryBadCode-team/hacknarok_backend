package com.tarbus.payload.entities.jpa;

import com.tarbus.payload.entity.FileObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "event_categories")
@Getter
@Setter
@NoArgsConstructor
public class EventCategory {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
    name = "UUID",
    strategy = "org.hibernate.id.UUIDGenerator"
  )
  private String id;
  private String name;
  @OneToOne()
  @JoinColumn(name = "image_id", referencedColumnName = "id")
  private FileObject image;
}
