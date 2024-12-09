package com.Harevich.core.orderline.dto;

import jakarta.validation.constraints.Positive;

public record OrderLineRequest (
        @Positive(message="product id must be positive")
        Long clothesId,
        @Positive(message="quantity must be positive")
        Integer quantity
){
}
