package com.almox.modules.departamento.model;

import com.almox.modules.auditoria.FiltroStatusAuditavel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltroDepartamentoDTO {
    private String nome;
    private FiltroStatusAuditavel filtroStatusAuditavel;
    private Long idProduto;
}
