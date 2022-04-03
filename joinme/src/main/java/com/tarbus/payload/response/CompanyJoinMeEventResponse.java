package com.tarbus.payload.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompanyJoinMeEventResponse extends JoinMeEventResponse {
  private String eventName;
  private String imageId;
}
