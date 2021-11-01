package com.almox.model.entidades;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
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

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Produto> produtosFornecidos;

    public Fornecedor(Long id, String razaoSocial, String cnpj, String nomeFantasia, Contato contato) {
        super(razaoSocial, cnpj, nomeFantasia, contato);
        this.id = id;
    }

}
