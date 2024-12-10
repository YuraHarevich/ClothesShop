package com.Harevich.core.kafka.consumer;
import com.Harevich.core.kafka.consumer.OrderLineRequest;

import java.util.List;

public record SupplyRequest(
        List<OrderLineRequest> orderLines
) {
}
