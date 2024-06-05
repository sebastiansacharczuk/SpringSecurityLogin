package com.sebsach.springlogin.repository;

import com.sebsach.springlogin.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
