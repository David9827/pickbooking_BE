package com.java.pickbooking.dto;

import com.java.pickbooking.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PostResponse {
    private Long postId;
    private String content;
    private String imageUrl;
    private LocalDateTime createdAt;
    private UserDto user;   // ⚡ dùng DTO thay vì entity
    private int reactionCount;
    private int commentCount;

    // tiện lợi: static factory chuyển từ entity
    public static PostResponse fromEntity(
            com.java.pickbooking.entity.Post post,
            int reactionCount,
            int commentCount
    ) {
        return new PostResponse(
                post.getPostId(),
                post.getContent(),
                post.getImageUrl(),
                post.getCreatedAt(),
                UserDto.fromEntity(post.getUser()), // ⚡ convert User -> UserDto
                reactionCount,
                commentCount
        );
    }
}
