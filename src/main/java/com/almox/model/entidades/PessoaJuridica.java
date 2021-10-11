package com.almox.model.entidades;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class PessoaJuridica extends Auditavel {

    @NotBlank(message = "{pessoa.razaoSocial.notblank}")
    @Column(name = "pesj_razaoSocial", nullable = false, unique = true)
    private String razaoSocial;

    @NotBlank(message = "{pessoa.cnpj.notblank}")
    @Column(name = "pesj_cnpj", nullable = false, unique = true)
    private String cnpj;

    @NotBlank
    @Column(name = "pesj_nomeFantasia", nullable = false, unique = true)
    private String nomeFantasia;

    @OneToOne
    @JoinColumn(name = "pesj_cont_id", referencedColumnName = "cont_id")
    private Contato contato;

}
