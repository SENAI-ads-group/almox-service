package org.almox.core.exceptions;

import com.google.common.collect.Sets;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApplicationRuntimeException extends RestException {

    public ApplicationRuntimeException(String... mensagens) {
        this(HttpStatus.INTERNAL_SERVER_ERROR, mensagens);
    }

    public ApplicationRuntimeException(HttpStatus httpStatus, String... mensagens) {
        super(httpStatus, Sets.newHashSet(mensagens));
    }
}
