package com.learn.micro.rating.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String mesaage) {
        super(mesaage);
    }
    public ResourceNotFoundException() {
        super("Not found");
    }
}
