package com.almox.model.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

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
    @Column(name = "plv_chave", unique = true)
    private String detalhe;

    @ManyToMany(fetch = FetchType.LAZY)
    @NotEmpty(message = "{palavraChave.produtos.notempty}")
    private Set<Produto> produtos;
}
