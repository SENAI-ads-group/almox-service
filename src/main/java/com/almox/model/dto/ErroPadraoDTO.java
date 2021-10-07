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
    private String erro;
    private String path;
    private List<String> mensagens;

    public ErroPadraoDTO() {
        timestamp = Instant.now();
    }

    public ErroPadraoDTO(Integer status, String erro, String path, String... mensagens) {
        this();
        this.status = status;
        this.erro = erro;
        this.path = path;
        this.mensagens = List.of(mensagens);
    }

    public ErroPadraoDTO(HttpStatus httpStatus, String erro, String path, String... mensagens) {
        this(httpStatus.value(), erro, path, mensagens);
    }

    public ErroPadraoDTO(HttpStatus httpStatus, String erro, String path, List<String> mensagens) {
        this(httpStatus, erro, path);
        this.mensagens = mensagens;
    }
}
