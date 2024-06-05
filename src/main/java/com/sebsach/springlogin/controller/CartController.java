package com.sebsach.springlogin.controller;

import com.sebsach.springlogin.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @GetMapping("/add/{bookId}/{quantity}")
    public String addToCart(@PathVariable int bookId, @PathVariable int quantity) {
        cartService.addToCart(bookId, quantity);
        return "redirect:/cart";
    }

    @GetMapping("")
    public String getCart(Model model) {
        model.addAttribute("cart", cartService.getCart());
        return "cart";
    }

    @GetMapping("/remove/{bookId}")
    public String removeFromCart(@PathVariable int bookId) {
        cartService.removeFromCart(bookId);
        return "redirect:/cart";
    }
}
