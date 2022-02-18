package com.almox.core.exceptions;

import com.almox.core.rest.ErroPadraoDTO;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
public class RestException extends RuntimeException {

    private static final long serialVersionUID = 2775477234133719856L;

    private HttpStatus httpStatus;
    private LocalDateTime dateTime;
    private String exception;
    private Set<String> messages;

    public RestException(HttpStatus httpStatus, Set<String> mensagens) {
        super(String.join(";", mensagens == null ? new HashSet<>() : mensagens));
        this.httpStatus = httpStatus;
        this.exception = getClass().getSimpleName();
        this.messages = mensagens;
        this.dateTime = LocalDateTime.now();
    }

    public ErroPadraoDTO getErroDTO() {
        return ErroPadraoDTO.builder()//
                .exception(exception)//
                .messages(messages)//
                .date(dateTime.toString())//
                .status(httpStatus.value())//
                .error(httpStatus.getReasonPhrase())//
                .build();
    }

}