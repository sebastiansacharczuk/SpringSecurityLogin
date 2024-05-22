package com.sebsach.springlogin.controller;

//import com.sebsach.springlogin.jwt.JwtUtils;
//import com.sebsach.springlogin.jwt.LoginRequest;
//import com.sebsach.springlogin.jwt.LoginResponse;
import com.sebsach.springlogin.model.User;
import com.sebsach.springlogin.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model, RedirectAttributes redirectAttributes) {

        String result = userService.registerUser(user);
        model.addAttribute("message", result);
        redirectAttributes.addAttribute("message", result);
        if (result.equals("success")) {
            return "redirect:/login";
        }
        return "register";
    }


//    @PostMapping("/register")
//    public String registerUser(
//            @ModelAttribute("user") User user,
//            @RequestParam("confirmPassword") String confirmPassword,
//            Model model
//    ) {
//        if (!user.getPassword().equals(confirmPassword)) {
//            model.addAttribute("errorMessage", "Passwords do not match.");
//            return "register";
//        }
//
//        try {
//            userService.registerUser(user);
//            return "redirect:/login";
//        } catch (Exception e) {
//            model.addAttribute("errorMessage", "An error occurred: " + e.getMessage());
//            return "register";
//        }
//    }
}
