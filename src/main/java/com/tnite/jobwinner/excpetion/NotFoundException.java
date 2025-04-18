package com.tnite.jobwinner.excpetion;


public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}