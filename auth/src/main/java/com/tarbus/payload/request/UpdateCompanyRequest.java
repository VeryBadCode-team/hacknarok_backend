package com.tarbus.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateCompanyRequest {
  private String id;
  private String name;
  private String website;
  private String phone;
  private String email;
  private String timezone;
  private String lang;
  private String avatarId;
}
