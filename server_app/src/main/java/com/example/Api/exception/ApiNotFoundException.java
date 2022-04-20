package com.example.Api.exception;

/**
 * Class that defines an exception that is thrown when the API can't find
 * the data that is asked from the client.
 */
public class ApiNotFoundException extends RuntimeException{
    public ApiNotFoundException(String message) {
        super(message);
    }
    public ApiNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
