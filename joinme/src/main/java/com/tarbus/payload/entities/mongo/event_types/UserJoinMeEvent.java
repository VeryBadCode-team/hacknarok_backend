package com.tarbus.payload.entities.mongo.event_types;

import com.tarbus.payload.entities.mongo.JoinMeEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "join_me_events")
public class UserJoinMeEvent extends JoinMeEvent {
}
