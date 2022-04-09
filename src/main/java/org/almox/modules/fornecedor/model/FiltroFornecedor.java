package org.almox.modules.fornecedor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.almox.modules.auditoria.FiltroStatusAuditavel;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FiltroFornecedor {
    public String nome;
    public String cnpj;
    public FiltroStatusAuditavel status;
}
