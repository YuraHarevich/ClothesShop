package com.Harevich.core.order.controller;

import com.Harevich.core.kafka.producer.OrderProducer;
import com.Harevich.core.order.dto.ErrorResponse;
import com.Harevich.core.order.dto.SupplyRequest;
import com.Harevich.core.order.exceptions.RunOutOfClothesException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {
    private final OrderProducer orderProducer;
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handle(EntityNotFoundException exception){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException exception){
        var errors = new HashMap<String,String>();
        exception.getBindingResult().getAllErrors()
                .forEach(error->{
                    var fieldName = ((FieldError)error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldName,errorMessage);
                });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(errors));
    }
    @ExceptionHandler(RunOutOfClothesException.class)
    public ResponseEntity<String> handle(RunOutOfClothesException exception){
        orderProducer.sendOrderConfirmation(
                new SupplyRequest(exception.getErrors())
        );
        log.info("sent supply request for {}",exception.getErrors()
                .stream()
                .map(error->error.clothesId())
                .collect(Collectors.toList()));
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}
