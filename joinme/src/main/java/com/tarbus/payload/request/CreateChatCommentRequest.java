package com.tarbus.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateChatCommentRequest {
  private String id;
  private String message;
}
