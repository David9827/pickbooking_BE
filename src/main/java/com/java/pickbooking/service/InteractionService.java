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
    public PostReaction react(Long userId, Long postId, String type) {
        User user = userRepo.findById(userId).orElseThrow();
        Post post = postRepo.findById(postId).orElseThrow();

        // Nếu user đã react -> update
        PostReaction existing = reactionRepo.findByPost_PostIdAndUser_UserId(postId, userId);
        if (existing != null) {
            // Nếu type giống nhau -> gỡ reaction (delete)
            if (existing.getType().equals(type)) {
                reactionRepo.delete(existing);
                return null; // hoặc return existing để FE biết đã gỡ
            }
            // Nếu khác -> update sang type mới
            existing.setType(type);
            return reactionRepo.save(existing);
        }

        // Nếu chưa có -> tạo mới
        PostReaction reaction = new PostReaction();
        reaction.setUser(user);
        reaction.setPost(post);
        reaction.setType(type);
        return reactionRepo.save(reaction);
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
