package org.almox.modules.pessoa.model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue(Pessoa.PESSOA_FISICA)
public class PessoaFisica extends Pessoa {

    @CPF(message = "{PessoaFisica}.cpf.CPF")
    @NotBlank(message = "{PessoaFisica.cpf.NotBlank}")
    @Column(name = "pess_pf_cpf")
    private String cpf;

    @Builder
    public PessoaFisica(UUID id, String nome, String email, String telefone, String logradouro, String complemento, String numero, String cep, String cidade, UF uf, String bairro, String cpf) {
        super(id, nome, email, telefone, logradouro, complemento, numero,
                cep, cidade, uf, bairro, TipoPessoa.PF);
        this.cpf = cpf;
    }

    @Override
    public TipoPessoa getTipo() {
        return TipoPessoa.PF;
    }
}
