package com.example.hitachimobilespringrest.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidDetailsException extends RuntimeException {
    private final HttpStatus status;

    public InvalidDetailsException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
