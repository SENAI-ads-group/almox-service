package org.almox.modules.pessoa.dto;

import org.almox.modules.pessoa.model.TipoPessoa;

public class PessoaFisicaDTO extends PessoaDTO {
    public String cpf;

    @Override
    public TipoPessoa getTipo() {
        return TipoPessoa.PF;
    }
}
