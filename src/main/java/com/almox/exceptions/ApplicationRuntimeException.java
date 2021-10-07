package com.almox.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApplicationRuntimeException extends RuntimeException {

    private final HttpStatus httpStatus;

    public ApplicationRuntimeException(HttpStatus httpStatus) {
        super(httpStatus.getReasonPhrase());
        this.httpStatus = httpStatus;
    }
}
