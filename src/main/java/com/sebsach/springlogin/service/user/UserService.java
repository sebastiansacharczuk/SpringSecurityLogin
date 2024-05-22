package com.sebsach.springlogin.service.user;

import com.sebsach.springlogin.model.Role;
import com.sebsach.springlogin.model.User;
import com.sebsach.springlogin.repository.RoleRepository;
import com.sebsach.springlogin.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;


    @Transactional
    public String registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return "failure";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName("USER").orElseGet(null);
        if (userRole != null) {
            user.getRoles().add(userRole);
        } else {
            Role role = new Role();
            role.setName("USER");
            user.getRoles().add(role);
        }
        userRepository.save(user);
        return "success";
    }

    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}