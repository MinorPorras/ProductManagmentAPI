package com.minor.productmanagement.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        List<ErrorField> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ErrorField(
                        error.getField(),
                        error.getDefaultMessage()
                ))
                .toList();
        ErrorResponse errorResponse = new ErrorResponse(400,"The request contains invalid fields", details);
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
