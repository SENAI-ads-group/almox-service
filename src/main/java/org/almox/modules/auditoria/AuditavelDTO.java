package org.almox.modules.auditoria;

import org.almox.modules.common.DTOSimples;

import java.time.LocalDateTime;
import java.util.UUID;

public class AuditavelDTO {
    public LocalDateTime dataCriacao;
    public LocalDateTime dataAlteracao;
    public LocalDateTime dataExclusao;
    public DTOSimples criadoPor;
    public DTOSimples alteradoPor;
    public DTOSimples excluidoPor;
    public StatusAuditavel situacao;
    public boolean excluido;
}
