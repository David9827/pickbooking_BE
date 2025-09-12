package com.java.authmanagerment.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter

@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String email;
    private String fullName;
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role =  Role.USER;  // ADMIN hoặc USER

    public enum Role {
        ADMIN, USER
    }

}
