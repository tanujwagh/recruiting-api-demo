package com.heavenhr.recruiting.module.recruiting.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class JobOfferAlreadyExistsException extends RuntimeException {

    public JobOfferAlreadyExistsException(String message){
        super(message);
    }

    public JobOfferAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}