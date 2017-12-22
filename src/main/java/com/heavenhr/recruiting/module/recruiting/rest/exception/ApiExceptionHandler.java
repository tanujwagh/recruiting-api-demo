package com.heavenhr.recruiting.module.recruiting.rest.exception;

import com.heavenhr.recruiting.module.recruiting.exceptions.JobApplicationNotFoundException;
import com.heavenhr.recruiting.module.recruiting.exceptions.JobOfferAlreadyExistsException;
import com.heavenhr.recruiting.module.recruiting.exceptions.JobOfferNotFoundException;
import com.heavenhr.recruiting.module.common.exception.ApiException;
import com.heavenhr.recruiting.module.recruiting.exceptions.JobApplicationAlreadyExistException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiException apiException = new ApiException(HttpStatus.BAD_REQUEST);
        apiException.setMessage("Validation error");
        apiException.setDebugMessage("Validation error");
        apiException.addValidationErrors(ex.getBindingResult().getFieldErrors());
        return handleExceptionInternal(ex, apiException, headers, apiException.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiException apiException = new ApiException(HttpStatus.BAD_REQUEST);
        apiException.setMessage("Malformed JSON request");
        apiException.setDebugMessage("Malformed JSON request");
        return handleExceptionInternal(ex, apiException, headers, apiException.getStatus(), request);
    }

    @ExceptionHandler({
            JobOfferNotFoundException.class,
            JobApplicationNotFoundException.class
    })
    protected ResponseEntity<Object> handleNotFoundException(
            RuntimeException ex) {
        ApiException apiException = new ApiException(HttpStatus.NOT_FOUND);
        apiException.setMessage(ex.getMessage());
        apiException.setDebugMessage(ex.getLocalizedMessage());
        return buildResponseEntity(apiException);
    }

    @ExceptionHandler({
            JobOfferAlreadyExistsException.class,
            JobApplicationAlreadyExistException.class
    })
    protected ResponseEntity<Object> handleAlreadyExistsException(
            RuntimeException ex) {
        ApiException apiException = new ApiException(HttpStatus.BAD_REQUEST);
        apiException.setMessage(ex.getMessage());
        apiException.setDebugMessage(ex.getLocalizedMessage());
        return buildResponseEntity(apiException);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiException apiException) {
        return new ResponseEntity<>(apiException, apiException.getStatus());
    }
}