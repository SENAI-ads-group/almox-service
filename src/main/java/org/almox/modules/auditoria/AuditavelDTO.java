package org.almox.modules.auditoria;

import org.almox.modules.common.DTOSimples;

import java.time.LocalDateTime;

public class AuditavelDTO {
    public LocalDateTime dataCriacao;
    public LocalDateTime dataAlteracao;
    public LocalDateTime dataExclusao;
    public OperadorAuditavel criadoPor;
    public OperadorAuditavel alteradoPor;
    public OperadorAuditavel excluidoPor;
    public String statusAuditoria;

    public static class OperadorAuditavel extends DTOSimples {
        public DTOSimples pessoa;
    }
}
