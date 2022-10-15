package com.glady.codinggame.exception;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class UserNotFoundException extends GladyException implements Serializable {


    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getMessage() {
        return "User not found";
    }
}
