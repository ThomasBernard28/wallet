package com.example.Api.exception;

public class ApiIncorrectException extends RuntimeException{

    public ApiIncorrectException(String message) {
        super(message);
    }

    public ApiIncorrectException(String message, Throwable cause) {
        super(message, cause);
    }
}
