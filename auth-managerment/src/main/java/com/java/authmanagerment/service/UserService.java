package com.java.authmanagerment.service;


import com.java.authmanagerment.entity.User;
import com.java.authmanagerment.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
    public List<User> getUsersById(List<Long> ids) {
        return userRepo.findAllById(ids);
    }

}
