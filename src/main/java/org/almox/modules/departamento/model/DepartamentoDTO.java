package org.almox.modules.departamento.model;

import org.almox.modules.auditoria.StatusAuditavel;
import org.almox.modules.operador.model.Operador;

import java.time.LocalDateTime;

public class DepartamentoDTO {
    public String descricao;
    public StatusAuditavel situacao;
    public LocalDateTime dataCriacao;
    public LocalDateTime dataExclusao;
    public LocalDateTime dataAlteracao;
    public Operador criadoPor;
    public Operador alteradoPor;
    public Operador excluidoPor;
}
