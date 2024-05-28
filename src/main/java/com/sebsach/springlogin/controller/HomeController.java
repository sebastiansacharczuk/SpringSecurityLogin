package com.sebsach.springlogin.controller;

import com.sebsach.springlogin.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

    @Autowired
    BookService bookService;

//    @GetMapping({"/home", "/"})
//    public String homeEndpoint() {
//        return "home";
//    }
    @RequestMapping(path = {"/","/home"}, method = RequestMethod.GET)
    public String catalog(Model model) {
        model.addAttribute("books", this.bookService.getAll());
        return "home";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String userEndpoint() {
        return "hello, user!";
    }
}
