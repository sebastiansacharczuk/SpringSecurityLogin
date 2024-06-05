package com.sebsach.springlogin.controller;


import com.sebsach.springlogin.model.order.Order;
import com.sebsach.springlogin.model.order.OrderItem;
import com.sebsach.springlogin.service.cart.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/submit")
    public String submitOrder() {
        Order order = orderService.submitOrder();
        return "redirect:/order/" + order.getId();
    }

    @GetMapping("/{orderId}")
    public String getOrder(@PathVariable Long orderId, Model model) {
        Order order = orderService.getOrder(orderId);
        double totalPrice = 0;
        for(OrderItem item : order.getItems()) {
            totalPrice = item.getBook().getPrice().doubleValue();
        }
        model.addAttribute("order", order);
        model.addAttribute("totalPrice", totalPrice);
        return "order";
    }
}
