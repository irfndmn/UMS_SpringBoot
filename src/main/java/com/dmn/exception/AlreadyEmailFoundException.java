package com.dmn.exception;

public class AlreadyEmailFoundException extends RuntimeException {
    public AlreadyEmailFoundException(String message) {
        super(message);
    }
}
