package com.tarbus.payload.mappers;

import com.tarbus.payload.dto.ChatCommentDto;
import com.tarbus.payload.entities.jpa.ChatComment;
import org.springframework.stereotype.Component;

@Component
public class ChatCommentDtoMapper {
  private final EventUserMapper eventUserMapper;

  public ChatCommentDtoMapper(EventUserMapper eventUserMapper) {
    this.eventUserMapper = eventUserMapper;
  }

  public ChatCommentDto mapToEntity(ChatComment chatComment) {
    ChatCommentDto chatCommentDto = new ChatCommentDto();
    chatCommentDto.setMessage(chatComment.getComment());
    chatCommentDto.setCreatedAt(chatComment.getCreatedAt());
    chatCommentDto.setUser(eventUserMapper.mapToEntity(chatComment.getUser()));
    return chatCommentDto;
  }
}
