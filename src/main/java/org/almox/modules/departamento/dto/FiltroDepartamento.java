package org.almox.modules.departamento.dto;

import lombok.Builder;
import org.almox.modules.auditoria.FiltroStatusAuditoria;

public class FiltroDepartamento extends FiltroStatusAuditoria {
    public String descricao;

    @Builder
    public FiltroDepartamento(String descricao, Tipo statusAuditoria) {
        super(statusAuditoria);
        this.descricao = descricao;
    }
}
