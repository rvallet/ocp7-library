package com.library.mslibrary.ws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoSuchResultException extends RuntimeException {

    public NoSuchResultException(String message) {
        super(message);
    }

}
