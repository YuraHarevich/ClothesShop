package com.Harevich.core.model;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
) {
}
