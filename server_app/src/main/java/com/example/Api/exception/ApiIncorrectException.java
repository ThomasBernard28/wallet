package com.example.Api.exception;

/**
 * Class that defines exception that is thrown when
 * we want to make an unsupported thing in the API
 */
public class ApiIncorrectException extends RuntimeException{

    public ApiIncorrectException(String message) {
        super(message);
    }

}
