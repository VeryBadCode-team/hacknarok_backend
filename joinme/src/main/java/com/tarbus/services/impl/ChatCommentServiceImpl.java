package com.tarbus.services.impl;

import com.tarbus.payload.entity.User;
import com.tarbus.payload.entities.jpa.ChatComment;
import com.tarbus.repositories.jpa.ChatCommentRepository;
import com.tarbus.services.AuthService;
import com.tarbus.services.ChatCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatCommentServiceImpl implements ChatCommentService {
  private final AuthService authService;
  private final ChatCommentRepository chatCommentRepository;

  @Autowired
  public ChatCommentServiceImpl(AuthService authService, ChatCommentRepository chatCommentRepository) {
    this.authService = authService;
    this.chatCommentRepository = chatCommentRepository;
  }

  @Override
  public void createChatCommentForEvent(String eventId, String message) {
    ChatComment chatComment = new ChatComment();
    System.out.println("BEFORE USER");
    User user = authService.getSessionUser();
    System.out.println("AFTER USER");
    chatComment.setId(UUID.randomUUID().toString());
    chatComment.setUser(user);
    chatComment.setComment(message);
    chatComment.setCreatedAt(new Date());
    chatComment.setEventId(eventId);
    System.out.println(chatComment);
    chatCommentRepository.save(chatComment);
  }

  @Override
  public List<ChatComment> getAllChatCommentsForEvent(String eventId) {
    return chatCommentRepository.findByEventIdOrderByCreatedAtDesc(eventId);
  }
}
