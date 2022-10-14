package org.almox.core.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Set;

@Getter
public class ApplicationRuntimeException extends RestException {

    public ApplicationRuntimeException(String... mensagens) {
        this(HttpStatus.INTERNAL_SERVER_ERROR, mensagens);
    }

    public ApplicationRuntimeException(HttpStatus httpStatus, String... mensagens) {
        super(httpStatus, Set.of(mensagens));
    }
}
