package com.java.authmanagerment.dto;

import com.java.authmanagerment.entity.User;
import lombok.Data;

@Data
public class UserDto {
    private Long userId;
    private String username;
    private String fullName;
    private String email;
    private String phone;
    private User.Role role;

    public static UserDto fromEntity(User user) {
        UserDto dto = new UserDto();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setRole(user.getRole());
        return dto;
    }
}
