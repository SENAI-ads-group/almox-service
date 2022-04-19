package org.almox.modules.departamento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.almox.modules.auditoria.FiltroStatusAuditoria;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FiltroDepartamento {
    public String descricao;
}
