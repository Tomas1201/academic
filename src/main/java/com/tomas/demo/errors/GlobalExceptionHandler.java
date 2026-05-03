package com.tomas.demo.errors;

import  org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;



@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleNotFound(ResourceNotFoundException ex) {
        ErrorDetails error = new ErrorDetails(
            LocalDateTime.now(),
            ex.getMessage(),
            "404 Not Found"
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}