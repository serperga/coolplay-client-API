package com.fakebook.coopay.coolplayclientAPI.exception;

public class CoolpayApiErrorException extends RuntimeException {

    public CoolpayApiErrorException(String message, Exception e) {
        super(message, e);
    }

    public CoolpayApiErrorException(String message) {
        super(message);
    }
}