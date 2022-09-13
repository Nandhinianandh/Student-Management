package com.example.sms.service.impl;

import com.example.sms.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userService {
    @Autowired
    private static UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }


    public User getUserByEmailAndPassword(String email, String password) {
        return userRepository.findUserByEmailAndPassword(email, password);
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }


    public User getUserById(Long userId) {
        return userRepository.findUserByUserId(userId);
    }
}