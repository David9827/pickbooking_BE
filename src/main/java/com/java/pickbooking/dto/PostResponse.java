package com.java.pickbooking.dto;

import com.java.pickbooking.entity.User;
import java.time.LocalDateTime;

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
    public Long getPostId() { return postId; }
    public void setPostId(Long postId) { this.postId = postId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public int getReactionCount() { return reactionCount; }
    public void setReactionCount(int reactionCount) { this.reactionCount = reactionCount; }

    public int getCommentCount() { return commentCount; }
    public void setCommentCount(int commentCount) { this.commentCount = commentCount; }
}
