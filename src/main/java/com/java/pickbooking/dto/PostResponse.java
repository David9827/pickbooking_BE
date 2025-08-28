package com.java.pickbooking.dto;

import com.java.pickbooking.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostResponse {
    private Long postId;
    private String content;
    private String imageUrl;
    private LocalDateTime createdAt;
    private User user;
    private int reactionCount;
    private int commentCount;

    // Constructor
    public PostResponse(Long postId, String content, String imageUrl,
                        LocalDateTime createdAt, User user,
                        int reactionCount, int commentCount) {
        this.postId = postId;
        this.content = content;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.user = user;
        this.reactionCount = reactionCount;
        this.commentCount = commentCount;
    }

    // Getters & Setters
}
