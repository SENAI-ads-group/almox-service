package org.almox.core.exceptions;

import org.springframework.http.HttpStatus;

public class RegraNegocioException extends ApplicationRuntimeException {

    public RegraNegocioException(String... mensagens) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, mensagens);
    }

    public RegraNegocioException(HttpStatus httpStatus, String... mensagens) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, mensagens);
    }
}
