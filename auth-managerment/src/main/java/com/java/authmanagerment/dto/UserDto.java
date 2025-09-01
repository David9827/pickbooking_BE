package com.java.authmanagerment.dto;

import com.java.authmanagerment.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class UserDto {
    private Long userId;
    private String username;
    private String fullName;
    private String email;
    private String phone;
    private User.Role role;

    public static UserDto fromEntity(User user) {
        if (user == null) return null;
        return new UserDto(
                user.getUserId(),
                user.getUsername(),
                user.getFullName(),
                user.getEmail(),
                user.getPhone(),
                user.getRole()  // nếu role là enum
        );
    }
    public static UserDto toDto(User user) {
        return new UserDto(
                user.getUserId(),
                user.getUsername(),
                user.getFullName(),
                user.getEmail(),
                user.getPhone(),
                user.getRole()
        );
    }

}
