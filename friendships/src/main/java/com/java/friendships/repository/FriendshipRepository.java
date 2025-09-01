package com.java.friendships.repository;


import com.java.friendships.entity.Friendship;
import com.java.friendships.entity.FriendshipStatus;
import com.java.friendships.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    // Lấy lời mời đến (PENDING)
    List<Friendship> findByReceiverAndStatus(User receiver, FriendshipStatus status);

    // Lấy lời mời đã gửi
    List<Friendship> findBySenderAndStatus(User sender, FriendshipStatus status);

    // Lấy quan hệ bạn bè
    List<Friendship> findBySenderOrReceiverAndStatus(User sender, User receiver, FriendshipStatus status);

    // Kiểm tra đã tồn tại quan hệ giữa 2 người chưa
    Optional<Friendship> findBySenderAndReceiver(User sender, User receiver);
}

