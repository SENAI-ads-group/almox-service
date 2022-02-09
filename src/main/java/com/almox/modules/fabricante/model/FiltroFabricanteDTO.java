package com.almox.modules.fabricante.model;

import com.almox.modules.auditoria.FiltroStatusAuditavel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FiltroFabricanteDTO {
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private FiltroStatusAuditavel filtroStatusAuditavel;

}
