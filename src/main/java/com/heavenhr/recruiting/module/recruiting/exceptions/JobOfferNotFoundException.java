package com.heavenhr.recruiting.module.recruiting.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class JobOfferNotFoundException extends RuntimeException {

    public JobOfferNotFoundException(String message){
        super(message);
    }

    public JobOfferNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
