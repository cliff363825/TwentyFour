package com.onevgo.upload.exceptions;

public class InvalidExtNameException extends RuntimeException {
    static final long serialVersionUID = 1L;
    public InvalidExtNameException() {
    }

    public InvalidExtNameException(String message) {
        super(message);
    }
}
