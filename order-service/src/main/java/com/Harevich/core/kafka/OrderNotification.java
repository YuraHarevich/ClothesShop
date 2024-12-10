package com.Harevich.core.kafka;

import com.Harevich.core.order.model.Order;

import java.util.List;

public record OrderNotification(
        Long orderId,
        List<Long> clothesIds
) {
}
