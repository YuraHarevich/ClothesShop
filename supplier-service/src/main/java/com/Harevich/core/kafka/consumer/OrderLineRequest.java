package com.Harevich.core.kafka.consumer;

import jakarta.validation.constraints.Positive;

public record OrderLineRequest(
        Long clothesId,
        Integer quantity
){
}
