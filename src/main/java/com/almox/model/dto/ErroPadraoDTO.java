package com.almox.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ErroPadraoDTO implements Serializable {
    private String exception;
    private int status;
    private String error;
    private String date;
    private String[] messages;

    @Builder
    public ErroPadraoDTO(String exception, int status, String error, Collection<String> messages, String date) {
        this.exception = exception;
        this.status = status;
        this.error = error;
        this.date = date;
        this.messages = messages.toArray(new String[0]);
    }

    public String[] getMessages(){
        return messages == null ? null : Arrays.copyOf(messages, messages.length);
    }
}
