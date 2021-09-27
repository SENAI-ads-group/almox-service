package com.almox.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ErroPadraoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;
    private List<String> messages;

    public ErroPadraoDTO() {
        timestamp = Instant.now();
    }

    public ErroPadraoDTO(Integer status, String error, String path, String... messages) {
        this();
        this.status = status;
        this.error = error;
        this.path = path;
        this.messages = List.of(messages);
    }

    public ErroPadraoDTO(HttpStatus httpStatus, String error, String path, String... messages) {
        this(httpStatus.value(), error, path, messages);
    }

    public ErroPadraoDTO(HttpStatus httpStatus, String error, String path, List<String> mensagens) {
        this(httpStatus, error, path);
        this.messages = mensagens;
    }
}
