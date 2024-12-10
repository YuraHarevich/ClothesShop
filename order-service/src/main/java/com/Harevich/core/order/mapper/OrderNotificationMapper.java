package com.Harevich.core.order.mapper;

import com.Harevich.core.kafka.OrderNotification;
import com.Harevich.core.order.model.Order;
import com.Harevich.core.orderline.model.OrderLine;

import java.util.stream.Collectors;

public class OrderNotificationMapper {

    public static OrderNotification toOrderNotification(Order order){
        return new OrderNotification(order.getId(),order.getOrderLines()
                .stream()
                .map(OrderLine::getClothesId)
                .collect(Collectors.toList()));
    }
}
