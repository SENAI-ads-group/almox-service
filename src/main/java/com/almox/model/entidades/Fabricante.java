package com.almox.model.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "fab_fabricante")

public class Fabricante extends PessoaJuridica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fab_id")
    private Long id;

    public Fabricante(Long id, String razaoSocial, String cnpj, String nomeFantasia, Contato contato) {
        super(razaoSocial,cnpj,nomeFantasia,contato);
        this.id = id;
    }

//    @OneToMany(mappedBy = "fabricante", fetch = FetchType.LAZY)
//    private Set<Produto>produtosFornecidos = new HashSet<>();
}
