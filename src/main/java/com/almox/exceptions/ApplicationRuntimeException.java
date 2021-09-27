package com.almox.exceptions;

import org.springframework.http.HttpStatus;

public class ApplicationRuntimeException extends RuntimeException {

    private HttpStatus httpStatus;

    public ApplicationRuntimeException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
