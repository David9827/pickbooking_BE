package com.java.pickbooking.repository;

import com.java.pickbooking.entity.PostReaction;
import com.java.pickbooking.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PostReactionRepository extends JpaRepository<PostReaction, Long> {
    Optional<PostReaction> findByUser_UserIdAndPost_PostId(Long userId, Long postId);
    List<PostReaction> findByPost_PostId(Long postId);
}
