package com.java.pickbooking.repository;

import com.java.pickbooking.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
