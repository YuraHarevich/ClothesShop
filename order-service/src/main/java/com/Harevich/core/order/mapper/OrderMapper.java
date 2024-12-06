package com.Harevich.core.order.mapper;

import com.Harevich.core.order.dto.OrderLineRequest;
import com.Harevich.core.order.dto.OrderRequest;
import com.Harevich.core.order.model.Order;

public class OrderMapper {
    public static Order toOrder(OrderRequest orderRequest){
        return Order.builder()
                .totalAmount(orderRequest.orderLines()
                        .stream().)
                .orderLines(orderRequest.orderLines());
    }
}
