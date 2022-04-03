package com.tarbus.services;

import com.tarbus.payload.entities.jpa.EventCategory;

import java.util.List;

public interface EventCategoryService {
    EventCategory createEventCategory(String eventCategoryName);
    EventCategory getEventCategoryById(String id);
    List<EventCategory> getAll();
}
