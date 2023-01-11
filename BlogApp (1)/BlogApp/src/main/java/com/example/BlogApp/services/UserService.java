package com.example.BlogApp.services;

import com.example.BlogApp.models.UserBlog;
import com.example.BlogApp.repositories.UserRepository;
import com.example.BlogApp.session.LoggedInUser;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final LoggedInUser loggedInUser;

    public UserService(UserRepository userRepository, LoggedInUser loggedInUser) {
        this.userRepository = userRepository;
        this.loggedInUser = loggedInUser;
    }

    public void loginUser(UserBlog userData) {
        if (loggedInUser.getId() != null) {
            throw new RuntimeException("Потребителят вече е влязъл!");
        }

        UserBlog userEntity = userRepository.findByUsername(userData.getUsername()).orElse(null);

        if (userEntity == null) {
            throw new RuntimeException("Потребителят не е намерен!");
        }

        loggedInUser.setUsername(userEntity.getUsername());
        loggedInUser.setId(userEntity.getId());
    }

    public void register(UserBlog userBlog) {
        if (loggedInUser.getId() != null) {
            throw new RuntimeException("Потребителят вече е влязъл!");
        }


        userRepository.save(userBlog);

        loggedInUser.setUsername(userBlog.getUsername());
        loggedInUser.setId(userBlog.getId());
    }

    public UserBlog findByUsername(String username) {
        UserBlog user = userRepository.findByUsername(username)
                .orElse(null);
        return user;
    }

    public void logout() {
        if (!this.isLogged()) {
            throw new RuntimeException("Потребителят не е влязъл!");
        }

        loggedInUser.setUsername(null);
        loggedInUser.setId(null);
    }


    public boolean isLogged() {
        return loggedInUser.getId() != null;
    }

    public UserBlog findLoggedInUser() {
        if (!this.isLogged()) {
            throw new RuntimeException("UПотребителят не е влязъл!");
        }

        UserBlog user = userRepository.findById(loggedInUser.getId()).orElse(null);

        if (user == null) {
            throw new RuntimeException("Потребителят не е намерен!");
        }

        return user;
    }
}
