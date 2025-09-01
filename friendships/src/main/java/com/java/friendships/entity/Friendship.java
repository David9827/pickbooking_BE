package com.java.friendships.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import com.java.friendships.entity.User;

@Getter
@Setter
@Entity
@Table(
        name = "friendships",
        uniqueConstraints = @UniqueConstraint(columnNames = {"sender_id", "receiver_id"})
)
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Người gửi lời mời
    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    // Người nhận lời mời
    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    // Trạng thái quan hệ
    @Enumerated(EnumType.STRING)
    private FriendshipStatus status = FriendshipStatus.PENDING;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void setLastUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
