package com.Harevich.core.clothes.dto;


import com.Harevich.core.clothes.model.Size;

import java.math.BigDecimal;

public record ClothesResponse(
    Long id,
    Size size,
    BigDecimal price,
    Integer availableQuantity
) {
}
