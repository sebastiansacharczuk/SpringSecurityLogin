package com.sebsach.springlogin.repository;

import com.sebsach.springlogin.model.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {

}
