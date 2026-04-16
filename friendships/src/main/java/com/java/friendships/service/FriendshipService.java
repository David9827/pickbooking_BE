package com.java.friendships.service;


import com.java.friendships.entity.Friendship;
import com.java.friendships.entity.FriendshipStatus;
import com.java.friendships.entity.User;
import com.java.friendships.repository.FriendshipRepository;
import com.java.friendships.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendshipService {

    private final FriendshipRepository friendshipRepository;
    private final UserRepository userRepository;

    public FriendshipService(FriendshipRepository friendshipRepository, UserRepository userRepository) {
        this.friendshipRepository = friendshipRepository;
        this.userRepository = userRepository;
    }

    // 🚀 Gửi lời mời kết bạn
    public Friendship sendRequest(Long senderId, Long receiverId) {
        if (senderId.equals(receiverId)) {
            throw new RuntimeException("Không thể tự gửi lời mời cho chính mình");
        }

        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        // Kiểm tra xem đã tồn tại quan hệ chưa
        Optional<Friendship> existing = friendshipRepository.findBySenderAndReceiver(sender, receiver);
        if (existing.isPresent()) {
            throw new RuntimeException("Lời mời đã tồn tại");
        }

        Friendship friendship = new Friendship();
        friendship.setSender(sender);
        friendship.setReceiver(receiver);
        friendship.setStatus(FriendshipStatus.PENDING);

        return friendshipRepository.save(friendship);
    }

    // 🚀 Chấp nhận lời mời
    public Friendship acceptRequest(Long requestId) {
        Friendship friendship = friendshipRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Lời mời không tồn tại"));
        friendship.setStatus(FriendshipStatus.ACCEPTED);
        return friendshipRepository.save(friendship);
    }

    // 🚀 Từ chối lời mời
    public Friendship rejectRequest(Long requestId) {
        Friendship friendship = friendshipRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Lời mời không tồn tại"));
        friendship.setStatus(FriendshipStatus.REJECTED);
        return friendshipRepository.save(friendship);
    }

    // 🚀 Hủy kết bạn
    public void removeFriend(Long friendshipId) {
        friendshipRepository.deleteById(friendshipId);
    }

    // 🚀 Danh sách bạn bè của user
    public List<Friendship> getFriends(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return friendshipRepository.findBySenderOrReceiverAndStatus(user, user, FriendshipStatus.ACCEPTED);
    }

    // 🚀 Danh sách lời mời đến
    public List<Friendship> getPendingRequests(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return friendshipRepository.findByReceiverAndStatus(user, FriendshipStatus.PENDING);
    }

    // 🚀 Danh sách lời mời đã gửi
    public List<Friendship> getSentRequests(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return friendshipRepository.findBySenderAndStatus(user, FriendshipStatus.PENDING);
    }
}

