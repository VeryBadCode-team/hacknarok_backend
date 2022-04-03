package com.tarbus.payload.mappers;

import com.tarbus.payload.dto.EventCategoryDto;
import com.tarbus.payload.entities.jpa.EventCategory;
import org.springframework.stereotype.Component;

@Component
public class EventCategoryDtoMapper {
  public EventCategoryDto mapToEntity(EventCategory eventCategory) {
    EventCategoryDto eventCategoryDto = new EventCategoryDto();
    eventCategoryDto.setId(eventCategory.getId());
    eventCategoryDto.setName(eventCategory.getName());
    if( eventCategory.getImage() != null ) {
      eventCategoryDto.setImageId(eventCategory.getImage().getId());
    }
    return eventCategoryDto;
  }
}
