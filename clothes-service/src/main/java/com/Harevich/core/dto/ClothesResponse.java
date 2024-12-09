package com.Harevich.core.dto;
import com.Harevich.core.model.Size;



import java.math.BigDecimal;

public record ClothesResponse(
    Long id,
    Size size,
    BigDecimal price,
    Integer availableQuantity
) {
}
