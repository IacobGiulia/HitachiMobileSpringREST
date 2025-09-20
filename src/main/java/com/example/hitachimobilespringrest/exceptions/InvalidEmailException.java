package com.example.hitachimobilespringrest.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidEmailException extends RuntimeException {
    private final HttpStatus status;

    public InvalidEmailException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
