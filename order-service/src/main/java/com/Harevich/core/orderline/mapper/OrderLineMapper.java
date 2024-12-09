package com.Harevich.core.orderline.mapper;

import com.Harevich.core.orderline.dto.OrderLineRequest;
import com.Harevich.core.orderline.model.OrderLine;

public class OrderLineMapper {
    public static OrderLine toOrderLine(OrderLineRequest request){
        return OrderLine.builder()
                .clothesId(request.clothesId())
                .quantity(request.quantity())
                .build();
    }
    public static OrderLineRequest toOrderLineRequest(OrderLine orderLine){
        return new OrderLineRequest(
                orderLine.getClothesId(),
                orderLine.getQuantity()
        );
    }
}
