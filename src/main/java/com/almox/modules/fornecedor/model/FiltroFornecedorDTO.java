package com.almox.modules.fornecedor.model;

import com.almox.modules.auditavel.FiltroStatusAuditavel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FiltroFornecedorDTO {
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private FiltroStatusAuditavel filtroStatusAuditavel;
}
