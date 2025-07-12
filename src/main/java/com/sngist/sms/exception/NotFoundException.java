package com.sngist.sms.exception;

public class NotFoundException extends RuntimeException{
    NotFoundException(String message){
        super(message);
    }
}
