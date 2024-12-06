package com.Harevich.core.order.dto;

import jakarta.validation.constraints.Positive;

public record OrderLineRequest (
        @Positive(message="product id must be positive")
        Integer productId,
        @Positive(message="quantity must be positive")
        double quantity
){
}
