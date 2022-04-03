package com.tarbus.services;

import com.tarbus.payload.entities.jpa.ChatComment;

import java.util.List;

public interface ChatCommentService {
   void createChatCommentForEvent(String eventId, String message);
   List<ChatComment> getAllChatCommentsForEvent(String eventId);
}
