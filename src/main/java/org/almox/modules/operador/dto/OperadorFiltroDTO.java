package org.almox.modules.operador.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class OperadorFiltroDTO {
    public String nome;
    public String email;
}
