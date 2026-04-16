package com.java.pickbooking.service;

import com.java.pickbooking.dto.UserDto;
import com.java.pickbooking.entity.User;
import com.java.pickbooking.exception.ErrorCode;
import com.java.pickbooking.exception.PostException;
import com.java.pickbooking.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new PostException(ErrorCode.ERROR_EXITED);
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10) ;
        user.setPassword((passwordEncoder.encode(user.getPassword())));
        return userRepository.save(user);
    }

    public Optional<User> login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
