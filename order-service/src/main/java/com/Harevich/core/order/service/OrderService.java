package com.Harevich.core.order.service;

import com.Harevich.core.clients.ClothesClient;
import com.Harevich.core.order.dto.OrderRequest;
import com.Harevich.core.order.exceptions.RunOutOfClothesException;
import com.Harevich.core.order.mapper.OrderMapper;
import com.Harevich.core.order.model.Order;
import com.Harevich.core.order.repository.OrderRepository;
import com.Harevich.core.orderline.dto.OrderLineRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final ClothesClient clothesClient;

    public Optional<Order> createOrder(OrderRequest orderRequest) {
       Order order = OrderMapper.toOrder(orderRequest);
       order.setTotalAmount(countTotal(orderRequest));
       List<OrderLineRequest> errors= clothesClient.purchaseClothes(orderRequest).errorOrderLines();
        if(errors!=null)
            return Optional.of(repository.saveAndFlush(order));
        else
            throw new RunOutOfClothesException("run out of clothes with id: "+errors
                    .stream()
                    .map(request -> request.clothesId())
                    .toList());


        //todo проблема если завпрос на покупку отправлен, а ответ не приходит
    }
    private BigDecimal countTotal(OrderRequest orderRequest){
        return orderRequest
                .orderLines()
                .stream()
                .map(orderLineRequest->(
                        BigDecimal.valueOf(orderLineRequest.quantity())
                                .multiply(
                                        clothesClient.findClothesById(orderLineRequest.clothesId())
                                                .orElseThrow(()->new EntityNotFoundException("Exception while trying to load products for the order"))
                                                .price()
                                )
                ))
                .reduce(BigDecimal.ZERO,BigDecimal::add);
    }

}
