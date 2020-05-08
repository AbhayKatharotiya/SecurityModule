package com.securitymodule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class RegistrationFailedException extends RuntimeException {

    public RegistrationFailedException(String message) {
        super(message);
    }

    public RegistrationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

}
