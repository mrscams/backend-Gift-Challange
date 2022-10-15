package com.glady.codinggame.exception;

import org.springframework.http.HttpStatus;

public abstract class GladyException extends RuntimeException  {

    public abstract HttpStatus getStatus();
    public abstract String getMessage();

}
