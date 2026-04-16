package com.java.chatapp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.OffsetDateTime;
import java.util.Date;

@Getter
@Setter
@Data
@Document(collection = "chat_messages")
public class ChatMessage {

    @Id
    private String id;

    private String roomId;
    private String senderId;
    private String content;
    private Date createdAt;

    // getters/setters
}
