package com.sebsach.springlogin.initializer;

import com.sebsach.springlogin.model.User;
import com.sebsach.springlogin.repository.RoleRepository;
import com.sebsach.springlogin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class UserInitializer implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Check if users already exist
//        if (userRepository.findByUsername("USER").isEmpty()) {
//            User user = new User();
//            user.setUsername("USER");
//            user.setPassword("PASSWORD");
//            userRepository.save(user);
//        }

    }
}