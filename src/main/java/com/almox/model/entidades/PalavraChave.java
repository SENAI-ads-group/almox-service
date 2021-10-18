package com.almox.model.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "plv_palavraChave")
public class PalavraChave extends EntidadePadrao{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plv_id")
    private Long id;

    @NotBlank(message = "palavraChave.detalhe.notblank")
    @Column(name = "plv_chave")
    private String detalhe;
}
