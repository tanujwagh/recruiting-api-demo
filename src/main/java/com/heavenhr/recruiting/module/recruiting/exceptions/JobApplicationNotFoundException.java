package com.heavenhr.recruiting.module.recruiting.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class JobApplicationNotFoundException extends RuntimeException {

    public JobApplicationNotFoundException(String message){
        super(message);
    }

    public JobApplicationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}