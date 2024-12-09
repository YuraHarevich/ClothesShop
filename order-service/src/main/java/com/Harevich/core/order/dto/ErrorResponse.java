package com.Harevich.core.order.dto;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
) {
}
