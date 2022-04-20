package org.almox.modules.fornecedor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FiltroFornecedor {
    public String nome;
    public String cnpj;
}
