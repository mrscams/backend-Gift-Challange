package com.glady.codinggame.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RestController
public class GenericExceptionHandler {

    @ExceptionHandler({IllegalAmountException.class, InsufficientCashException.class,CompanyNotFoundException.class,UserNotFoundException.class})
    private ResponseEntity<String> handle(GladyException  ge) {
        return new ResponseEntity<>(ge.getMessage(), ge.getStatus());
    }

}