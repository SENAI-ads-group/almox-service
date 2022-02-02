package com.almox.modules.fornecedor.model;


import com.almox.modules.pessoa.Contato;
import com.almox.modules.pessoa.PessoaJuridica;
import com.almox.modules.produto.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "forn_fornecedor")
public class Fornecedor extends PessoaJuridica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "forn_id")
    private Long id;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Produto> produtosFornecidos;

    public Fornecedor(Long id, String razaoSocial, String cnpj, String nomeFantasia, Contato contato) {
        super(razaoSocial, cnpj, nomeFantasia, contato);
        this.id = id;
    }
}
