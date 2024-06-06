package com.app_Friendly.app.Exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.context.MessageSourceAware;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

public interface ValidationExceptionHan extends MessageSourceAware {
    ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                        HttpHeaders headers,
                                                        HttpStatus status,
                                                        WebRequest request);

    ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
                                                              HttpHeaders headers,
                                                              HttpStatus status,
                                                              WebRequest request);
}
