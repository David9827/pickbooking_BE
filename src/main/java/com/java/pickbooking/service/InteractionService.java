package com.java.pickbooking.service;

import com.java.pickbooking.entity.*;
import com.java.pickbooking.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InteractionService {
    private final PostReactionRepository reactionRepo;
    private final CommentRepository commentRepo;
    private final UserRepository userRepo;
    private final PostRepository postRepo;

    public InteractionService(PostReactionRepository reactionRepo,
                              CommentRepository commentRepo,
                              UserRepository userRepo,
                              PostRepository postRepo) {
        this.reactionRepo = reactionRepo;
        this.commentRepo = commentRepo;
        this.userRepo = userRepo;
        this.postRepo = postRepo;
    }

    // Thả reaction (like, tym, haha…)
    public PostReaction react(Long userId, Long postId, PostReaction.ReactionType type) {
        User user = userRepo.findById(userId).orElseThrow();
        Post post = postRepo.findById(postId).orElseThrow();
        return reactionRepo.findByUser_UserIdAndPost_PostId(userId, postId)
                .map(r -> { r.setType(type); return reactionRepo.save(r); })
                .orElseGet(() -> {
                    PostReaction newR = new PostReaction();
                    newR.setUser(user);
                    newR.setPost(post);
                    newR.setType(type);
                    return reactionRepo.save(newR);
                });
    }

    public List<PostReaction> getReactions(Long postId) {
        return reactionRepo.findByPost_PostId(postId);
    }

    // Comment
    public Comment addComment(Long userId, Long postId, String content) {
        User user = userRepo.findById(userId).orElseThrow();
        Post post = postRepo.findById(postId).orElseThrow();
        Comment c = new Comment();
        c.setUser(user);
        c.setPost(post);
        c.setContent(content);
        return commentRepo.save(c);
    }

    public List<Comment> getComments(Long postId) {
        return commentRepo.findByPost_PostIdOrderByCreatedAtAsc(postId);
    }
}
