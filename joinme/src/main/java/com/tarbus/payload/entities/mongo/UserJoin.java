package com.tarbus.payload.entities.mongo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserJoin {
  private String userId;
  private Date joinTime;

  public UserJoin(String userId, Date joinTime) {
    this.userId = userId;
    this.joinTime = joinTime;
  }
}
