package com.tarbus.payload.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompanyJoinMeEventDetailsResponse extends JoinMeEventDetailsResponse {
  private String eventName;
  private String imageId;
}
