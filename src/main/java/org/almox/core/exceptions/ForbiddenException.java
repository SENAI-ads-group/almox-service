package org.almox.core.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Collections;

public class ForbiddenException extends RestException {
    public ForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN, Collections.singleton(message));
    }
}
