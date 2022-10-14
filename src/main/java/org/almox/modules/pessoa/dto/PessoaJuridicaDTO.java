package org.almox.modules.pessoa.dto;

import org.almox.modules.pessoa.model.TipoPessoa;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotBlank;

public class PessoaJuridicaDTO extends PessoaDTO {
    public String razaoSocial;
    public String cnpj;
    public String nomeFantasia;

    @Override
    public TipoPessoa getTipo() {
        return TipoPessoa.PJ;
    }
}
