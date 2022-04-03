package com.tarbus.payload.request;

import com.tarbus.payload.request.CreateJoinMeEventRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserJoinMeEventRequest extends CreateJoinMeEventRequest {
  private String categoryId;
}
