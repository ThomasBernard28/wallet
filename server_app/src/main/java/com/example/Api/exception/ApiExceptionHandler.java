package com.example.Api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * This class is the Exception handler of the API service.
 * This class manage the different customs exceptions that the API may encounter.
 */

@ControllerAdvice
public class ApiExceptionHandler {

    /**
     * This method handles NOT FOUND exception in case we are looking
     * for a data that cannot be found in the db.
     * @param e ApiNotFoundException Exception type
     * @return a response containing the body of the exception (message, status, date and time of the exception)
     */
    @ExceptionHandler(value = {ApiNotFoundException.class})
    public ResponseEntity<Object> handleApiNotFoundException(ApiNotFoundException e){
        // 1. Create payload containing exception details
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                e.getMessage(),
                notFound,
                LocalDateTime.now()
        );
        // 2. Return response entity
        return new ResponseEntity<>(apiException, notFound);
    }

    /**
     * This method handles Incorrect request exception type in the case we
     * would make a request that cannot be executed.
     * @param e ApiIncorrectException exception type
     * @return a response containing the body of the exception (message, status, date and time of the exception)
     */
    @ExceptionHandler(value = {ApiIncorrectException.class})
    public ResponseEntity<Object> handleApiIncorrectException(ApiIncorrectException e){
        HttpStatus conflict = HttpStatus.CONFLICT;
        ApiException apiException = new ApiException(
                e.getMessage(),
                conflict,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiException, conflict);
    }
}
