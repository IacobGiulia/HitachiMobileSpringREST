package com.example.hitachimobilespringrest.exceptions;

import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends RuntimeException {
    private final HttpStatus status;

    public CustomerNotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
