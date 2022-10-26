package com.company.exception;

public class IsEmptyException extends RuntimeException{

    public IsEmptyException(String message) {
        super(message);
    }

    public IsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
