package com.example.Api.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * The objective of this class is to define a pattern for
 * customized exception
 */
public class ApiException {
    private final String message;

    private final HttpStatus httpStatus;
    private final LocalDateTime dateTime;

    /**
     * Constructor
     * @param message The message of the error
     * @param httpStatus The http error status
     * @param dateTime The date and time the error occured
     */
    public ApiException(String message, HttpStatus httpStatus, LocalDateTime dateTime) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
