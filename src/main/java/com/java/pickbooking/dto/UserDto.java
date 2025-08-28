package com.java.pickbooking.dto;

import com.java.pickbooking.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long userId;

    private String username;

    private String password;

    private String email;
    private String fullName;
    private String phone;

    @Enumerated(EnumType.STRING)
    private User.Role role = User.Role.USER;

    // Getter & Setter
    public enum Role {
        USER, ADMIN
    }
}
