package com.Harevich.core.kafka.producer;

import com.Harevich.core.kafka.consumer.OrderLineRequest;

import java.util.List;

public record ClothesSupply(
        List<OrderLineRequest> orderLines
) {
}
