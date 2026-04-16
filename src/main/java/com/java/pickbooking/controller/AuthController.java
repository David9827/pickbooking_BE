
// đã chuyển sang auth-managerment ở 8081


package com.java.pickbooking.controller;

import com.java.pickbooking.dto.response.ApiResponse;
import com.java.pickbooking.entity.User;
import com.java.pickbooking.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Cho phép frontend React gọi API
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Đăng ký tài khoản
    @PostMapping("/register")
    public ApiResponse<User> register(@RequestBody @Valid User user) {

        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.register(user));

        return apiResponse;

    }

    // Đăng nhập
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Optional<User> loggedInUser = userService.login(user.getUsername(), user.getPassword());
        if (loggedInUser.isPresent()) {
            return ResponseEntity.ok(loggedInUser.get());
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}
