package org.almox.modules.departamento.dto;

import org.almox.modules.auditoria.AuditavelDTO;
import org.almox.modules.common.DTOSimples;
import org.almox.modules.pessoa.dto.PessoaFisicaDTO;

import java.util.Set;
import java.util.UUID;

public class DepartamentoDTO {
    public UUID id;
    public String descricao;
    public AuditavelDTO auditoria;
    public Set<OperadorDepartamento> operadores;

    public static class OperadorDepartamento extends DTOSimples {
        public PessoaFisicaDTO pessoa;
    }
}
