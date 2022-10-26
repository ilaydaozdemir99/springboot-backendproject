package com.company.project.producingwebservice.exception;

public class IsEmptyException extends RuntimeException{

    public IsEmptyException(String message) {
        super(message);
    }

    public IsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
