package com.sebsach.springlogin.service.cart;

import com.sebsach.springlogin.model.cart.Cart;
import com.sebsach.springlogin.model.cart.CartItem;
import com.sebsach.springlogin.model.User;
import com.sebsach.springlogin.model.order.Order;
import com.sebsach.springlogin.model.order.OrderItem;
import com.sebsach.springlogin.model.order.OrderStatus;
import com.sebsach.springlogin.repository.OrderRepository;
import com.sebsach.springlogin.service.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;

@RequiredArgsConstructor
@Service
public class OrderService {


    private final UserService userService;
    private final CartService cartService;
    private final OrderRepository orderRepository;

    @Transactional
    public Order submitOrder() {
        User user = userService.getCurrentUser();
        Cart cart = user.getCart();
        Order order = new Order();
        order.setDate(new Date(System.currentTimeMillis()));
        order.setStatus(OrderStatus.SUBMITTED);
        order.setUser(user);

        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());

            orderItem.setQuantity(cartItem.getQuantity());
            order.getItems().add(orderItem);
        }
        cart.getItems().clear();
        cartService.saveCart(cart);
        return orderRepository.save(order);
    }

    @Transactional
    public Order getOrder(long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Transactional
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }


}
