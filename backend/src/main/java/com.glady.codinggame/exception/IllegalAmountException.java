package com.glady.codinggame.exception;


import org.springframework.http.HttpStatus;

public class IllegalAmountException extends GladyException {

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.PRECONDITION_FAILED;
    }

    @Override
    public String getMessage() {
        return "Amount of Cash is not accepted";
    }

}
