package com.java.chatapp.dto;

import lombok.Data;

@Data
public class ChatMessagePayload {
    private String type;
    private String roomId;
    private String senderId;
    private String content;
}
