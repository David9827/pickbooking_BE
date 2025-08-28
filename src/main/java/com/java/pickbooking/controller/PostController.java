package com.java.pickbooking.controller;

import com.java.pickbooking.dto.PostResponse;
import com.java.pickbooking.entity.Post;
import com.java.pickbooking.entity.User;
import com.java.pickbooking.repository.CommentRepository;
import com.java.pickbooking.repository.PostReactionRepository;
import com.java.pickbooking.repository.PostRepository;
import com.java.pickbooking.repository.UserRepository;
import com.java.pickbooking.service.PostService;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*")
public class PostController {
    private final PostService postService;
    private final UserRepository userRepository;
    private final PostReactionRepository reactionRepo;
    private final CommentRepository commentRepo;
    private final PostRepository postRepository;

    public PostController(PostService postService, UserRepository userRepository,
                          PostRepository postRepository,
                          PostReactionRepository reactionRepo,
                          CommentRepository commentRepo) {
        this.postService = postService;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.reactionRepo = reactionRepo;
        this.commentRepo = commentRepo;
    }

    // Tạo bài viết mới
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostRequest  req) {
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = new Post();
        post.setUser(user);  // Lấy user đầy đủ từ DB
        post.setContent(req.getContent());
        post.setImageUrl(req.getImageUrl());

        return ResponseEntity.ok(postService.createPost(post));
    }

    // Lấy tất cả bài viết
    @GetMapping
    public List<PostResponse> getAllPosts() {
        List<Post> posts = postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));

        return posts.stream().map(p -> new PostResponse(
                p.getPostId(),
                p.getContent(),
                p.getImageUrl(),
                p.getCreatedAt(),
                p.getUser(),
                reactionRepo.findByPost_PostId(p.getPostId()).size(),
                commentRepo.findByPost_PostIdOrderByCreatedAtAsc(p.getPostId()).size()
        )).toList();
    }

    // Lấy chi tiết bài viết
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long id) {
        return postRepository.findById(id)
                .map(p -> new PostResponse(
                        p.getPostId(),
                        p.getContent(),
                        p.getImageUrl(),
                        p.getCreatedAt(),
                        p.getUser(),
                        reactionRepo.findByPost_PostId(p.getPostId()).size(),
                        commentRepo.findByPost_PostIdOrderByCreatedAtAsc(p.getPostId()).size()
                ))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    //lay bai viet theo user_Id: -->
    @GetMapping("/userId/{user_id}")
    public ResponseEntity<List<PostResponse>> getPostByUserId(@PathVariable Long user_id) {
        List<Post> posts = postRepository.findByUser_UserId(user_id, Sort.by(Sort.Direction.DESC, "createdAt") );

        if (posts.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<PostResponse> responses = posts.stream().map(p -> new PostResponse(
                        p.getPostId(),
                        p.getContent(),
                        p.getImageUrl(),
                        p.getCreatedAt(),
                        p.getUser(),
                        reactionRepo.findByPost_PostId(p.getPostId()).size(),
                        commentRepo.findByPost_PostIdOrderByCreatedAtAsc(p.getPostId()).size()
                )).toList();
        return ResponseEntity.ok(responses);
    }

    // Xoá bài viết
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
