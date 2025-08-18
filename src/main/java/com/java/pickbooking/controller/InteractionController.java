package com.java.pickbooking.controller;

import com.java.pickbooking.entity.*;
import com.java.pickbooking.service.InteractionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*")
public class InteractionController {
    private final InteractionService service;

    public InteractionController(InteractionService service) {
        this.service = service;
    }

    // Thả reaction
    @PostMapping("/{postId}/react")
    public ResponseEntity<PostReaction> react(@PathVariable Long postId,
                                              @RequestParam Long userId,
                                              @RequestParam PostReaction.ReactionType type) {
        return ResponseEntity.ok(service.react(userId, postId, type));
    }

    // Lấy danh sách reaction
    @GetMapping("/{postId}/reactions")
    public ResponseEntity<Map<String, Object>> getReactions(@PathVariable Long postId) {
        List<PostReaction> reactions = service.getReactions(postId);
        Map<String, Long> counts = new HashMap<>();
        for (PostReaction.ReactionType t : PostReaction.ReactionType.values()) {
            counts.put(t.name(), reactions.stream().filter(r -> r.getType() == t).count());
        }
        Map<String, Object> resp = new HashMap<>();
        resp.put("total", reactions.size());
        resp.put("details", counts);
        return ResponseEntity.ok(resp);
    }

    // Thêm comment
    @PostMapping("/{postId}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable Long postId,
                                              @RequestParam Long userId,
                                              @RequestParam String content) {
        return ResponseEntity.ok(service.addComment(userId, postId, content));
    }

    // Lấy danh sách comment
    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long postId) {
        return ResponseEntity.ok(service.getComments(postId));
    }
}
