package org.example.config;

import org.example.service.exception.Exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for controllers.
 * This class handles exceptions thrown by the controllers and provides a consistent response structure.
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    /**
     * Handles custom application exceptions.
     *
     * @param e the exception that was thrown.
     * @return a response entity containing the exception message and a BAD_REQUEST (400) status code.
     */
    @ExceptionHandler(Exceptions.class)
    public ResponseEntity<String> handle(Exceptions e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}