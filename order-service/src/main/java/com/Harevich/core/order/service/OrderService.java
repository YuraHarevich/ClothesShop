package com.Harevich.core.order.service;

import com.Harevich.core.order.dto.OrderRequest;
import com.Harevich.core.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;

    public Integer createOrder(OrderRequest orderRequest) {
        repository.
    }
}
