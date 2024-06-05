package com.sebsach.springlogin.model.cart;

import com.sebsach.springlogin.model.Book;
import com.sebsach.springlogin.model.User;
import com.sebsach.springlogin.model.cart.CartItem;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    @OneToOne(mappedBy = "cart")
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }
    public void addItem(Book book, int quantity) {
        Optional<CartItem> existingItem = items.stream()
                .filter(item -> item.getBook().equals(book))
                .findFirst();
        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            CartItem newItem = new CartItem();
            newItem.setBook(book);
            newItem.setQuantity(quantity);
            newItem.setCart(this);
            items.add(newItem);
        }
    }

    public void removeItem(Book book) {
        items.removeIf(item -> item.getBook().equals(book));
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
