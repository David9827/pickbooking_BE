package com.java.authmanagerment.service;


import com.java.authmanagerment.entity.User;
import com.java.authmanagerment.repository.UserRepository;
import com.java.authmanagerment.dto.response.ApiResponse;
import com.java.authmanagerment.exception.ErrorCode;
import com.java.authmanagerment.exception.PostException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.BeanNameViewResolver;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BeanNameViewResolver beanNameViewResolver;

    public UserService(UserRepository userRepository, BeanNameViewResolver beanNameViewResolver) {
        this.userRepository = userRepository;
        this.beanNameViewResolver = beanNameViewResolver;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public List<User> getUsersById(List<Long> ids) {
        return userRepository.findAllById(ids);
    }

    public User register(User user) {

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new PostException(ErrorCode.ERROR_EXITED);
        }
        userRepository.save(user);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10) ;
        user.setPassword((passwordEncoder.encode(user.getPassword())));

        return user;
    }

    public Optional<User> login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }


}
