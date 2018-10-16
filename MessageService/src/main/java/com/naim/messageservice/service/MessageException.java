package com.naim.messageservice.service;

public class MessageException extends RuntimeException{

    String message;

    public MessageException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
