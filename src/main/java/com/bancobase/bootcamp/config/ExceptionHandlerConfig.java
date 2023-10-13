package com.bancobase.bootcamp.config;

import com.bancobase.bootcamp.exceptions.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerConfig extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> businessExceptionHandler(Exception e) {
        return new ResponseEntity<>("Business exception: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFoundExceptionHandler(Exception e) {
        return new ResponseEntity<>("Resource not found exception: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServiceProviderException.class)
    public ResponseEntity<String> serviceProviderExceptionHandler(Exception e) {
        return new ResponseEntity<>("Service provider exception: " + e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }
}
