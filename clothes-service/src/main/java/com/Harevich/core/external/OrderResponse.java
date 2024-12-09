package com.Harevich.core.external;

import java.util.List;

public record OrderResponse(
        List<OrderLineRequest> errorOrderLines
) {
}
