package com.Harevich.core.orderline.dto;

import com.Harevich.core.order.model.Order;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Positive;

public record OrderLineResponse(
        @Positive(message="product id must be positive")
        Long id,
        @Positive(message="product id must be positive")
        Long clothesId,
        @Positive(message="quantity must be positive")
        Integer quantity
) {
}
