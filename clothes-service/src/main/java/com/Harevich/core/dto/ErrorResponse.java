package com.Harevich.core.dto;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
) {
}
