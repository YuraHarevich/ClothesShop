package com.Harevich.core.order.dto;

import com.Harevich.core.orderline.dto.OrderLineRequest;

import java.util.List;

public record OrderResponse(
        List<OrderLineRequest> errorOrderLines
) {
}
