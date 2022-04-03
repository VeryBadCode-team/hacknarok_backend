package com.tarbus.repositories.jpa;

import com.tarbus.payload.entities.jpa.ChatComment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatCommentRepository extends CrudRepository<ChatComment, String> {
  List<ChatComment> findByEventIdOrderByCreatedAtDesc(String eventId);
}
