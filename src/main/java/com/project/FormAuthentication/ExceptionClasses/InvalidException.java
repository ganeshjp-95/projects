package com.project.FormAuthentication.ExceptionClasses;

public class InvalidException extends RuntimeException{
    public InvalidException(String message){
        super(message);
    }
}
