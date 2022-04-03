package com.tarbus.payload.mappers;

import com.tarbus.payload.dto.EventUserDto;
import com.tarbus.payload.entity.User;
import com.tarbus.services.UserRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventUserMapper {
  private final UserRateService userRateService;

  @Autowired
  public EventUserMapper(UserRateService userRateService) {
    this.userRateService = userRateService;
  }

  public EventUserDto mapToEntity(User user) {
    EventUserDto eventUserDto = new EventUserDto();
    eventUserDto.setId(user.getId());
    eventUserDto.setFirstName(user.getFirstName());
    eventUserDto.setLastName(user.getLastName());
    eventUserDto.setRate(userRateService.getUserRatesAvg(user.getId()));
    if( user.getAvatar() != null ) {
      eventUserDto.setAvatarId(user.getAvatar().getId());
    }
    return eventUserDto;
  }
}
