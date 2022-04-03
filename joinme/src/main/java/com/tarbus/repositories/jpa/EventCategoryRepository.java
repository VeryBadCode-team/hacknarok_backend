package com.tarbus.repositories.jpa;

import com.tarbus.payload.entities.jpa.EventCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventCategoryRepository extends CrudRepository<EventCategory, String> {
}
