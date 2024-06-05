package com.sebsach.springlogin.service.cart;

import com.sebsach.springlogin.dao.IBookDAO;
import com.sebsach.springlogin.model.Book;
import com.sebsach.springlogin.model.cart.Cart;
import com.sebsach.springlogin.model.User;
import com.sebsach.springlogin.repository.CartRepository;
import com.sebsach.springlogin.service.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private IBookDAO bookRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public Cart getCart() {
        User user = userService.getCurrentUser();
        return user.getCart();
    }

    @Transactional
    public Cart addToCart(int bookId, int quantity) {
        Cart cart = getCart();
        Book book = bookRepository.getById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        cart.addItem(book, quantity);
        return saveCart(cart);
    }

    @Transactional
    public Cart removeFromCart(int bookId) {
        Cart cart = getCart();
        Book book = bookRepository.getById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        cart.removeItem(book);
        return saveCart(cart);
    }

    @Transactional
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }
}
