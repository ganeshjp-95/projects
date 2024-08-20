package com.project.FormAuthentication.ExceptionClasses;

public class SignatureException extends RuntimeException{
    public SignatureException(String message){
        super(message);
    }
}
