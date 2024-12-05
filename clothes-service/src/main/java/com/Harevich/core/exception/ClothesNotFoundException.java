package com.Harevich.core.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClothesNotFoundException extends RuntimeException{
    private final String message;
}
