package com.Harevich.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ClothesRequest(
        @NotBlank(message = "name is mandatory")
        @Schema(description = "name", example = "T-shirt")
        String name,
        @NotBlank(message = "color is mandatory")
        @Schema(description = "color", example = "red")
        String color,
        @NotBlank(message = "size is mandatory")
        @Enumerated(EnumType.STRING)
        @Schema(description = "size", example = "M")
        Size size,
        @JsonProperty("price")
        @NotBlank(message = "price is mandatory")
        @Min(value = 0, message = "price must be greater than 0")
        @Schema(description = "price", example = "12.1")
        BigDecimal price,
        @JsonProperty("quantity")
        @NotBlank(message = "availableQuantity is mandatory")
        @Min(value = 0, message = "quantity must be greater than 0")
        @Schema(description = "availableQuantity", example = "10")
        Integer availableQuantity
) {
}
