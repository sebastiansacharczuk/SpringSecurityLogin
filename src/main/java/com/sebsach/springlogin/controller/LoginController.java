package com.sebsach.springlogin.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;


@Controller
public class LoginController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String loginUser(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            Model model,
                            HttpServletRequest request,
                            HttpServletResponse response) throws ServletException, IOException {
        try {
            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            // Set authentication in security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // If authentication is successful, redirect to "/home"
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            System.out.println("User " + userDetails.getUsername() + " logged in successfully");

            return "redirect:/home";
        } catch (Exception e) {
            // If authentication fails, redirect back to login page with error message
            model.addAttribute("errorMessage", "Invalid username or password");
            return "login";
        }
    }
    @GetMapping("/login")
    public String showLoginForm(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model
    ) {
        if (error != null) {
            model.addAttribute("error", error);
        }
        if (logout != null) {
            model.addAttribute("logout", logout);
        }
        return "login";
    }

    //    @PostMapping("/signin")
//    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
//        Authentication authentication;
//        try {
//            authentication = authenticationManager
//                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//        } catch (AuthenticationException exception) {
//            Map<String, Object> map = new HashMap<>();
//            map.put("message", "Bad credentials");
//            map.put("status", false);
//            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
//        }
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .toList();
//
//        LoginResponse response =  new LoginResponse(userDetails.getUsername(), roles, jwtToken);
//        return ResponseEntity.ok(response);
//    }
}
