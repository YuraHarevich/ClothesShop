package com.Harevich.core.order.controller;

import com.Harevich.core.order.exceptions.RunOutOfClothesException;
import com.Harevich.core.order.model.Order;
import com.Harevich.core.order.dto.OrderRequest;

import com.Harevich.core.order.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Tag(name = "Order")
public class OrderController {
    private final OrderService service;
    @PostMapping("newOrder")
    public ResponseEntity<Order> newOrder(@RequestBody OrderRequest orderRequest){
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.createOrder(orderRequest).get());
    }
}
