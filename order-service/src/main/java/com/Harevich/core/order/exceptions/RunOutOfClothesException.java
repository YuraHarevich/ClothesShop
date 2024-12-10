package com.Harevich.core.order.exceptions;

import com.Harevich.core.orderline.dto.OrderLineRequest;
import lombok.*;

import java.util.List;
@Getter
@Setter
public class RunOutOfClothesException extends RuntimeException {
    private List<OrderLineRequest> errors;
    private String message;
    public RunOutOfClothesException(List<OrderLineRequest> errors) {
        this.errors = errors;
        message = "run out of clothes with id: "+errors
                .stream()
                .map(request -> request.clothesId())
                .toList();
    }
}
