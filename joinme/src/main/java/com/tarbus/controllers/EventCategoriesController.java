package com.tarbus.controllers;

import com.tarbus.exceptions.RequestException;
import com.tarbus.payload.dto.EventCategoryDto;
import com.tarbus.payload.entities.jpa.EventCategory;
import com.tarbus.payload.mappers.EventCategoryDtoMapper;
import com.tarbus.services.EventCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/joinme")
public class EventCategoriesController {
  private final EventCategoryService eventCategoryService;
  private final EventCategoryDtoMapper eventCategoryDtoMapper;

  @Autowired
  public EventCategoriesController(EventCategoryService eventCategoryService, EventCategoryDtoMapper eventCategoryDtoMapper) {
    this.eventCategoryService = eventCategoryService;
    this.eventCategoryDtoMapper = eventCategoryDtoMapper;
  }

  @PreAuthorize("hasRole('USER')")
  @GetMapping({"/categories"})
  public ResponseEntity<?> getAll() {
    try {
      List<EventCategory> categories = eventCategoryService.getAll();
      List<EventCategoryDto> response = categories.stream().map(eventCategoryDtoMapper::mapToEntity).collect(Collectors.toList());
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (RequestException e) {
      return e.toResponseEntity();
    }
  }
}
