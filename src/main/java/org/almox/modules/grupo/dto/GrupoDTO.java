package org.almox.modules.grupo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.almox.modules.auditoria.AuditavelDTO;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GrupoDTO {
    public UUID id;
    public String descricao;
    public AuditavelDTO auditoria;
}
