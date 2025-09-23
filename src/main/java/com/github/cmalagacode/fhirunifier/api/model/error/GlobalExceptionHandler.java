package com.github.cmalagacode.fhirunifier.api.model.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.naming.ServiceUnavailableException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceUnavailableException.class)
    public ErrorResponse handleServiceUnavailable(ServiceUnavailableException ex, WebRequest request) {
        return ErrorResponse.create(ex, HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
    }
}
