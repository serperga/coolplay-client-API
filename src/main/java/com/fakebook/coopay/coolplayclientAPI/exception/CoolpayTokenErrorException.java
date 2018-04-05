package com.fakebook.coopay.coolplayclientAPI.exception;

import org.springframework.http.HttpStatus;

public class CoolpayTokenErrorException extends RuntimeException {

    public CoolpayTokenErrorException(Exception e) {
        super("HTTPStatus: " + HttpStatus.UNAUTHORIZED, e);
    }
}
