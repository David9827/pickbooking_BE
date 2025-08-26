package com.java.pickbooking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReactionRequest {
    private Long postId;
    private Long userId;
    private String type;
}

