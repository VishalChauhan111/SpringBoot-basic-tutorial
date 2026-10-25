package com.mindurownbusiness.springboottutorial.springboottutorial.exception;

public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound(String message) {
        super(message);
    }
}
