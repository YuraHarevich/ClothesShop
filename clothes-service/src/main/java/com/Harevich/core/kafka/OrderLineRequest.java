package com.Harevich.core.kafka;

public record OrderLineRequest(
        Long clothesId,
        Integer quantity
){
}
