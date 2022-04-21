package com.almox.modules.fabricante.model;

import com.almox.modules.pessoa.Contato;
import com.almox.modules.pessoa.PessoaJuridica;
import com.almox.modules.produto.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fab_fabricante")
public class Fabricante extends PessoaJuridica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fab_id")
    private Long id;

    @Builder
    public Fabricante(Long id, String razaoSocial, String cnpj, String nomeFantasia, Contato contato) {
        super(razaoSocial, cnpj, nomeFantasia, contato);
        this.id = id;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "fabricante", fetch = FetchType.LAZY)
    private Set<Produto> produtosFornecidos;
}
