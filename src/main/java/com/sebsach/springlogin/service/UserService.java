package com.sebsach.springlogin.service;

import com.sebsach.springlogin.model.User;
import com.sebsach.springlogin.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Transactional
    public User registerUser(User user) throws Exception {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new Exception("Username already exists.");
        }

        // Encrypt the user's password before saving
        user.setPassword(passwordEncoder().encode(user.getPassword()));

        // Save the user to the database and return the saved entity
        return userRepository.save(user);
    }


    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}