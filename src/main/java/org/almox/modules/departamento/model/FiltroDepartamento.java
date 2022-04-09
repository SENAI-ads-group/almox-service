package org.almox.modules.departamento.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.almox.modules.auditoria.FiltroStatusAuditavel;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FiltroDepartamento {
    public String nome;
    public FiltroStatusAuditavel filtroStatusAuditavel;
}
