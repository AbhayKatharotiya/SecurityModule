package com.securitymodule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RequestUnauthorizedException extends RuntimeException{

    public RequestUnauthorizedException(String message) {
        super(message);
    }

    public RequestUnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

}
