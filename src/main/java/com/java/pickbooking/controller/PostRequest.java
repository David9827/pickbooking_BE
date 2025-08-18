package com.java.pickbooking.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {
    private Long userId;
    private String content;
    private String imageUrl;

    // Getter & Setter
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
