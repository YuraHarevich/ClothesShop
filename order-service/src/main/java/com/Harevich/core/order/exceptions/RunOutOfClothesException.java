package com.Harevich.core.order.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RunOutOfClothesException extends RuntimeException {
    private final String message;
}
