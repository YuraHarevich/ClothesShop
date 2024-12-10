package com.Harevich.core.kafka;


import java.util.List;

public record OrderNotification(
        Long orderId,
        List<Long> clothesIds
) {
}
