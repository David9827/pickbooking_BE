package com.java.pickbooking.repository;

import com.java.pickbooking.entity.PostReaction;
import com.java.pickbooking.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost_PostIdOrderByCreatedAtAsc(Long postId);
}
