package com.java.pickbooking.entity;

import com.java.pickbooking.exception.ErrorCode;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Size(min = 8, message = "PASSWORD_VALIDATION_ERROR")
    @Column(nullable = false)
    private String password;

    private String email;
    private String fullName;
    private String phone;


    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    // Getter & Setter
    public enum Role {
        USER, ADMIN
    }
}
