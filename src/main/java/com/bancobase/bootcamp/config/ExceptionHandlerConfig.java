package com.bancobase.bootcamp.config;

import com.bancobase.bootcamp.exceptions.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerConfig extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> businessExceptionHandler(Exception e) {
        return new ResponseEntity<>("Business exception: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
