package com.java.pickbooking.controller;

import com.java.pickbooking.dto.PostResponse;
import com.java.pickbooking.dto.UserDto;
import com.java.pickbooking.entity.Post;
import com.java.pickbooking.entity.User;
import com.java.pickbooking.repository.PostRepository;
import com.java.pickbooking.repository.UserRepository;
import com.java.pickbooking.repository.CommentRepository;
import com.java.pickbooking.repository.PostReactionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/search")
@CrossOrigin(origins = "*")
public class SearchController {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PostReactionRepository reactionRepo;
    private final CommentRepository commentRepo;

    public SearchController(UserRepository userRepository,
                            PostRepository postRepository,
                            PostReactionRepository reactionRepo,
                            CommentRepository commentRepo) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.reactionRepo = reactionRepo;
        this.commentRepo = commentRepo;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> search(@RequestParam String keyword) {
        Map<String, Object> result = new HashMap<>();

        // 🔎 Tìm user
        List<UserDto> users = userRepository.findByUsernameContainingIgnoreCaseOrFullNameContainingIgnoreCase(keyword, keyword)
                .stream()
                .map(u -> new UserDto(u.getUserId(), u.getUsername(), u.getFullName(), u.getEmail(), u.getPhone(), u.getRole().name()))
                .toList();

        // 🔎 Tìm post
        List<PostResponse> posts = postRepository.findByContentContainingIgnoreCase(keyword)
                .stream()
                .map(p -> new PostResponse(
                        p.getPostId(),
                        p.getContent(),
                        p.getImageUrl(),
                        p.getCreatedAt(),
                        UserDto.fromEntity(p.getUser()),
                        reactionRepo.findByPost_PostId(p.getPostId()).size(),
                        commentRepo.findByPost_PostIdOrderByCreatedAtAsc(p.getPostId()).size()
                ))
                .toList();

        result.put("users", users);
        result.put("posts", posts);

        return ResponseEntity.ok(result);
    }
}
