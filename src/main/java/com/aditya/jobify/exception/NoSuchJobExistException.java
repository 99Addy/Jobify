package com.aditya.jobify.exception;

public class NoSuchJobExistException extends RuntimeException {
    private String message;

    public NoSuchJobExistException() {};

    public NoSuchJobExistException(String msg){
        super(msg);
        this.message = msg;
    }
}
