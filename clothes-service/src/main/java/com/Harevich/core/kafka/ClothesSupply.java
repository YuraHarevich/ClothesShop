package com.Harevich.core.kafka;
import java.util.List;

public record ClothesSupply(
        List<OrderLineRequest> orderLines
) {
}
