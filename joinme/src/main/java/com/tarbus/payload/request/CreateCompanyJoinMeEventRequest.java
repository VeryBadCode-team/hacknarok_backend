package com.tarbus.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateCompanyJoinMeEventRequest extends CreateJoinMeEventRequest {
  private String eventName;
  private String companyId;

  @Override
  public String toString() {
    return "CreateCompanyJoinMeEventRequest{" +
      "type=" + type +
      ", authorId='" + authorId + '\'' +
      ", eventDescription='" + eventDescription + '\'' +
      ", eventTime=" + eventTime +
      ", maxUsers=" + maxUsers +
      ", eventName='" + eventName + '\'' +
      ", companyId='" + companyId + '\'' +
      '}';
  }
}
