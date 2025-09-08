package com.java.friendships.controller;

import com.java.friendships.entity.Friendship;
import com.java.friendships.service.FriendshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friends")
@CrossOrigin(origins = "*")
public class FriendshipController {

    private final FriendshipService friendshipService;

    public FriendshipController(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    /**
     * Gửi lời mời kết bạn
     */
    @PostMapping("/request")
    public ResponseEntity<Friendship> sendFriendRequest(
            @RequestParam Long senderId,
            @RequestParam Long receiverId
    ) {
        return ResponseEntity.ok(friendshipService.sendRequest(senderId, receiverId));
    }

    /**
     * Chấp nhận lời mời kết bạn
     */
    @PostMapping("/{friendshipId}/accept")
    public ResponseEntity<Friendship> acceptRequest(@PathVariable Long friendshipId) {
        return ResponseEntity.ok(friendshipService.acceptRequest(friendshipId));
    }

    /**
     * Từ chối lời mời kết bạn
     */
    @PostMapping("/{friendshipId}/reject")
    public ResponseEntity<Void> rejectRequest(@PathVariable Long friendshipId) {
        friendshipService.removeFriend(friendshipId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Lấy danh sách bạn bè của user
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<Friendship>> getFriends(@PathVariable Long userId) {
        return ResponseEntity.ok(friendshipService.getFriends(userId));
    }

    /**
     * Lấy danh sách lời mời kết bạn chờ xác nhận
     */
    @GetMapping("/{userId}/requests")
    public ResponseEntity<List<Friendship>> getPendingRequests(@PathVariable Long userId) {
        return ResponseEntity.ok(friendshipService.getPendingRequests(userId));
    }
    @DeleteMapping("/{friendshipId}/remove")
    public ResponseEntity<Void> removeRequest(@PathVariable Long friendshipId){
        friendshipService.removeFriend(friendshipId);
        return ResponseEntity.noContent().build();
    }
}

