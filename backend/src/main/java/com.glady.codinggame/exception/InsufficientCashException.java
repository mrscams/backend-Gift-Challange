package com.glady.codinggame.exception;


import org.springframework.http.HttpStatus;

public class InsufficientCashException extends GladyException {


    @Override
    public HttpStatus getStatus() {
        return HttpStatus.PRECONDITION_FAILED;
    }

    @Override
    public String getMessage() {
        return "Company does not have enough treseory";
    }

}
