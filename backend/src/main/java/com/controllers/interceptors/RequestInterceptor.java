package com.controllers.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice()
public class RequestInterceptor {

    private static final Logger log = LoggerFactory.getLogger(RequestInterceptor.class);
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleExceptions(Exception e) {
        log.error("e: ", e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
