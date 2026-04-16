package com.java.pickbooking.dto;

import com.java.pickbooking.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
public class UserDto {
    private Long userId;
    private String username;
    private String fullName;
    private String email;
    private String phone;
    private String role;


    public static UserDto fromEntity(User user) {
        if (user == null) return null;
        return new UserDto(
                user.getUserId(),
                user.getUsername(),
                user.getFullName(),
                user.getEmail(),
                user.getPhone(),
                user.getRole().name()// nếu role là enum
        );
    }
}
