package com.tarbus.payload.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventCategoryDto {
  private String id;
  private String name;
  private String imageId;
}
