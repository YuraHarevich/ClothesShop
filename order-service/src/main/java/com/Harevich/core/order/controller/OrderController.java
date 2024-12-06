package com.Harevich.core.order.controller;

import com.Harevich.core.order.model.Order;
import com.Harevich.core.order.dto.OrderRequest;

import com.Harevich.core.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;
    @PostMapping("newOrder")
    public ResponseEntity<Order> newOrder(@RequestBody OrderRequest orderRequest){
        service.createOrder(orderRequest);
    }
}
