package com.sparta.schedule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Exception {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleCustomException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND + "\n" + e.getMessage());
    }
}
