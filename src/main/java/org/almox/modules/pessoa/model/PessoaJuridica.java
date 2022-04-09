package org.almox.modules.pessoa.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue(Pessoa.PESSOA_JURIDICA)
public class PessoaJuridica extends Pessoa {

    @NotBlank(message = "{pessoa.razaoSocial.notblank}")
    @Column(name = "pess_pj_razaosocial")
    private String razaoSocial;

    @CNPJ
    @NotBlank(message = "{pessoa.cnpj.notblank}")
    @Column(name = "pess_pj_cnpj")
    private String cnpj;

    @NotBlank
    @Column(name = "pess_pj_nomefantasia")
    private String nomeFantasia;

    @Builder
    public PessoaJuridica(UUID id, String nome, String email, String telefone, String logradouro,
                          String complemento, String numero, String cep, String cidade, UF uf, String bairro,
                          String razaoSocial, String cnpj, String nomeFantasia) {
        super(id, nome, email, telefone, logradouro, complemento, numero,
                cep, cidade, uf, bairro, TipoPessoa.PJ);
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
    }

    @Override
    public TipoPessoa getTipo() {
        return TipoPessoa.PJ;
    }
}
