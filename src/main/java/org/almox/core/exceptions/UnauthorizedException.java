package org.almox.core.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Collections;

public class UnauthorizedException extends RestException {
    public UnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED, Collections.emptySet());
    }
}
