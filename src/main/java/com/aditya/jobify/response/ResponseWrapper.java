package com.aditya.jobify.response;

public class ResponseWrapper<T> {
    private T data;
    private String message;

    public ResponseWrapper(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public ResponseWrapper(T data) {
        this.data = data;
        this.message = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

