package com.sebsach.springlogin.controller;

import com.sebsach.springlogin.model.Book;
import com.sebsach.springlogin.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("book", new Book());
        return "book-form";
    }

    // @PostMapping("/add")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute Book book) {
        this.bookService.saveOrUpdate(book);
        return "redirect:/catalog";
    }

    @RequestMapping(path = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable int id, Model model) {
        Optional<Book> bookOptional = this.bookService.getById(id);
        if (bookOptional.isEmpty()) {
            return "redirect:/book/catalog";
        }
        else {
            model.addAttribute("book", bookOptional.get());
            return "book-form";
        }
    }
    @RequestMapping(path = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable int id, @ModelAttribute Book book) {
        this.bookService.saveOrUpdate(book);
        return "redirect:/book/catalog";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam int id) {
        bookService.deleteById(id);
        return "redirect:/book/catalog";
    }

    @RequestMapping(path = "/catalog", method = RequestMethod.GET)
    public String catalog(Model model) {
        model.addAttribute("books", this.bookService.getAll());
        return "catalog";
    }

}

