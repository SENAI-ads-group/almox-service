package org.almox.modules.pessoa.dto;

import org.almox.modules.pessoa.model.TipoPessoa;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotBlank;

public class CriarPessoaJuridicaDTO extends PessoaDTO {
    @NotBlank(message = "{Pessoa.razaoSocial.NotBlank}")
    public String razaoSocial;

    @CNPJ(message = "{Pessoa.cnpj.CNPJ}")
    @NotBlank(message = "{Pessoa.cnpj.NotBlank}")
    public String cnpj;

    @NotBlank(message = "{Pessoa.nomeFantasia.NotBlank}")
    public String nomeFantasia;

    @Override
    public TipoPessoa getTipo() {
        return TipoPessoa.PJ;
    }
}
