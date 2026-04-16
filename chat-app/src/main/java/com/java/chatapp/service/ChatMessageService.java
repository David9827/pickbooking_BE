package com.java.chatapp.service;

import com.java.chatapp.dto.ChatMessagePayload;
import com.java.chatapp.entity.ChatMessage;
import com.java.chatapp.repository.ChatMessageRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    public ChatMessage saveChatMessage(ChatMessagePayload payload) {
        log.info("Saving chat message: roomId={}, senderId={}, content={}",
                payload.getRoomId(), payload.getSenderId(), payload.getContent());
        ChatMessage doc = new ChatMessage();
        doc.setRoomId(payload.getRoomId());
        doc.setSenderId(payload.getSenderId());
        doc.setContent(payload.getContent());
        doc.setCreatedAt(new Date());

        ChatMessage saved = chatMessageRepository.save(doc);

        log.info("Saved chat message with id={}", saved.getId());
        return saved;
    }


    public List<ChatMessage> getMessagesForRoom(String roomId) {
        return chatMessageRepository.findByRoomIdOrderByCreatedAtAsc(roomId);
    }
}
