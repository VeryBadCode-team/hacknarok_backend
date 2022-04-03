package com.tarbus.services.impl;

import com.google.common.collect.Lists;
import com.tarbus.exceptions.BadRequestException;
import com.tarbus.payload.entities.jpa.EventCategory;
import com.tarbus.repositories.jpa.EventCategoryRepository;
import com.tarbus.services.EventCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventsCategoryServiceImpl implements EventCategoryService {

  private final EventCategoryRepository eventCategoryRepository;

  @Autowired
  public EventsCategoryServiceImpl(EventCategoryRepository eventCategoryRepository) {
    this.eventCategoryRepository = eventCategoryRepository;
  }


  @Override
  public EventCategory createEventCategory(String eventCategoryName) {
    EventCategory eventCategory = new EventCategory();
    eventCategory.setId(UUID.randomUUID().toString());
    eventCategory.setName(eventCategoryName);
    return eventCategoryRepository.save(eventCategory);
  }

  @Override
  public EventCategory getEventCategoryById(String id) {
    Optional<EventCategory> optionalCategory = eventCategoryRepository.findById(id);
    if( optionalCategory.isPresent() ) {
      return optionalCategory.get();
    }
    throw new BadRequestException("Event category with id " + id + " does not exist");
  }

  @Override
  public List<EventCategory> getAll() {
    return Lists.newArrayList(eventCategoryRepository.findAll());
  }
}
