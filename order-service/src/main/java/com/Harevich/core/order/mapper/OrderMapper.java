package com.Harevich.core.order.mapper;

import com.Harevich.core.order.dto.OrderRequest;
import com.Harevich.core.order.dto.OrderResponse;
import com.Harevich.core.order.model.Order;
import com.Harevich.core.orderline.mapper.OrderLineMapper;

import java.util.stream.Collectors;



public class OrderMapper {
    public static Order toOrder(OrderRequest orderRequest){
        return Order.builder()
                .orderLines(orderRequest.orderLines()
                        .stream()
                        .map(orderLineRequest-> OrderLineMapper.toOrderLine(orderLineRequest))
                        .collect(Collectors.toList())).build();
    }

    public static OrderRequest toOrderRequest(Order order){
        return new OrderRequest(
                order.getOrderLines()
                        .stream()
                        .map(OrderLineMapper::toOrderLineRequest)
                        .collect(Collectors.toList()));
    }

}
