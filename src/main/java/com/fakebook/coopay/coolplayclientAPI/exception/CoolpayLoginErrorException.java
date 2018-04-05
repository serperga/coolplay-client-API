package com.fakebook.coopay.coolplayclientAPI.exception;

public class CoolpayLoginErrorException extends RuntimeException {

    public CoolpayLoginErrorException(String message, Exception e) {
        super(message, e);
    }
}