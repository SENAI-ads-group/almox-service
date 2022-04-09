package org.almox.modules.pessoa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.almox.modules.pessoa.model.TipoPessoa;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaFiltroDTO {
    public TipoPessoa tipo;
    public String nome;
    public String email;
}
