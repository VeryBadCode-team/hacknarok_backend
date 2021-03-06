package com.tarbus.services.impl;


import java.util.*;

import com.tarbus.payload.entity.mongo.room.ChatRoom;
import com.tarbus.payload.mappers.ChatUserDtoMapper;
import com.tarbus.services.ChatRoomService;
import com.tarbus.services.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarbus.payload.entity.mongo.message.ChatMessage;
import com.tarbus.payload.entity.mongo.message.MessageStatus;
import com.tarbus.repositories.mongo.ChatMessageRepository;
import com.tarbus.services.ChatMessageService;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {
  private final ChatMessageRepository repository;
  private final UserDataService userDataService;
  private final ChatUserDtoMapper chatUserDtoMapper;
  private final ChatRoomService chatRoomService;

  @Autowired
  public ChatMessageServiceImpl(ChatMessageRepository repository, UserDataService userDataService, ChatUserDtoMapper chatUserDtoMapper, ChatRoomService chatRoomService) {
    this.repository = repository;
    this.userDataService = userDataService;
    this.chatUserDtoMapper = chatUserDtoMapper;
    this.chatRoomService = chatRoomService;
  }

  @Override
  public ChatMessage saveMessage(ChatMessage chatMessage) {
    chatMessage.setStatus(MessageStatus.sent);
    chatMessage.setCreatedAt(new Date());
    chatMessage.setUpdatedAt(new Date());
    repository.save(chatMessage);
    return chatMessage;
  }

  @Override
  public ChatMessage findLastChatMessageForRoom(String roomId) {
    return repository.findTopByRoomIdOrderByCreatedAtDesc(roomId);
  }

  @Override
  public List<ChatMessage> findChatMessages(String roomId) {
    return repository.findByRoomIdOrderByCreatedAtDesc(roomId);
  }

  @Override
  public ChatMessage findById(String id) {
    return repository.findById(id).map(chatMessage -> {
      chatMessage.setStatus(MessageStatus.delivered);
      return repository.save(chatMessage);
    }).orElse(null);
  }
}
