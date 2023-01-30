package com.biletx.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(VehicleDoesNotException.class)
    public ResponseEntity<ExceptionResponse> handleVehicle(VehicleDoesNotException exception) {
        return ResponseEntity.ok(new ExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(UserDoesNotException.class)
    public ResponseEntity<ExceptionResponse> handleUser(UserDoesNotException exception) {
        return ResponseEntity.ok(new ExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(BasketDoesNotException.class)
    public ResponseEntity<ExceptionResponse> handleBasket(BasketDoesNotException exception) {
        return ResponseEntity.ok(new ExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST));
    }
    @ExceptionHandler(TicketDoesNotException.class)
    public ResponseEntity<ExceptionResponse> handleBasket(TicketDoesNotException exception) {
        return ResponseEntity.ok(new ExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST));
    }


}
