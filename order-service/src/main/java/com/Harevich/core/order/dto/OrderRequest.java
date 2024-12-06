package com.Harevich.core.order.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record OrderRequest(
        @NotEmpty(message = "you should at least purchase one product")
        List<OrderLineRequest> orderLines
) {
}
