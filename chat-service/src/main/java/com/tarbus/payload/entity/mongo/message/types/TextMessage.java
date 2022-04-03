package com.tarbus.payload.entity.mongo.message.types;


import com.tarbus.payload.entity.mongo.message.ChatMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "chat_messages")
@Getter
@Setter
@NoArgsConstructor
public class TextMessage extends ChatMessage {
  /**
   * Contains message content sent by user
   */
  private String text;
}
