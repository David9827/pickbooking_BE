package com.java.friendships;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class FriendshipsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FriendshipsApplication.class, args);
    }

}
