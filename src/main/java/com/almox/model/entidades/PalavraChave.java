package com.almox.model.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class PalavraChave extends EntidadePadrao{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plv_id")
    private Long id;

    @NotBlank(message = "palavraChave.detalhe.notblank")
    @Column(name = "plv_chave")
    private String detalhe;

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void setId(Long id) {

    }
}
