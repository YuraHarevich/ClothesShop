package com.Harevich.core.order.service;

import com.Harevich.core.clients.ClothesClient;
import com.Harevich.core.kafka.producer.NotificationProducer;
import com.Harevich.core.order.dto.OrderRequest;
import com.Harevich.core.order.exceptions.RunOutOfClothesException;
import com.Harevich.core.order.mapper.OrderMapper;
import com.Harevich.core.order.mapper.OrderNotificationMapper;
import com.Harevich.core.order.model.Order;
import com.Harevich.core.order.repository.OrderRepository;
import com.Harevich.core.orderline.dto.OrderLineRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final ClothesClient clothesClient;
    private final NotificationProducer notificationProducer;

    public Optional<Order> createOrder(OrderRequest orderRequest) {
       Order order = OrderMapper.toOrder(orderRequest);
       order.setTotalAmount(countTotal(orderRequest));
       List<OrderLineRequest> errors= clothesClient.purchaseClothes(orderRequest).errorOrderLines();
        if(errors==null) {
            Optional<Order> optional = Optional.of(repository.saveAndFlush(order));
            notificationProducer.sendNotificationConfirmation(OrderNotificationMapper.toOrderNotification(optional.get()));
            return optional;
        }
        else
            throw new RunOutOfClothesException(errors);

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
