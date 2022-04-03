package com.tarbus.payload.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventUserDto {
  private String id;
  private String firstName;
  private String lastName;
  private double rate;
  private String avatarId;
}
