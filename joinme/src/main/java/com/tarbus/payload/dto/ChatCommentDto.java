package com.tarbus.payload.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ChatCommentDto {
    private String message;
    private Date createdAt;
    private EventUserDto user;
}
