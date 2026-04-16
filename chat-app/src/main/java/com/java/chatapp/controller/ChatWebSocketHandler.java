package com.java.chatapp.controller;

import com.java.chatapp.dto.ChatMessagePayload;
import com.java.chatapp.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class ChatWebSocketHandler extends TextWebSocketHandler {
    private final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();
    private final ChatMessageService chatMessageService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session){
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payloadText = message.getPayload();

        ChatMessagePayload payload = objectMapper.readValue(payloadText, ChatMessagePayload.class);

        if ("CHAT".equalsIgnoreCase(payload.getType())) {
            try {
                chatMessageService.saveChatMessage(payload);
            } catch (Exception e) {
                e.printStackTrace(); // tạm thời cho hiện lỗi ra console
            }
        }

        for (WebSocketSession s : sessions){
            if (s.isOpen())
                s.sendMessage(new TextMessage(payloadText));
        }
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
    }
}
