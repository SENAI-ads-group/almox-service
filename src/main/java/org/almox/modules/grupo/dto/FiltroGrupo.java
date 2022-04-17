package org.almox.modules.grupo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.almox.modules.auditoria.FiltroStatusAuditoria;

public class FiltroGrupo extends FiltroStatusAuditoria {
    public String descricao;

    @Builder
    public FiltroGrupo(String descricao, FiltroStatusAuditoria.Tipo statusAuditoria) {
        super(statusAuditoria);
        this.descricao = descricao;
    }
}
