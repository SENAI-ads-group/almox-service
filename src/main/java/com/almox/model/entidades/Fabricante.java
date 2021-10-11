package com.almox.model.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

    public Fabricante(Long id, String razaoSocial, String cnpj, String nomeFantasia, Contato contato) {
        super(razaoSocial, cnpj, nomeFantasia, contato);
        this.id = id;
    }

//    @OneToMany(mappedBy = "fabricante", fetch = FetchType.LAZY)
//    private Set<Produto>produtosFornecidos = new HashSet<>();
}
