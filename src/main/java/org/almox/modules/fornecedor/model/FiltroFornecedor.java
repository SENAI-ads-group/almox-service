package org.almox.modules.fornecedor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.almox.modules.auditoria.FiltroStatusAuditoria;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FiltroFornecedor {
    public String nome;
    public String cnpj;
    public FiltroStatusAuditoria status;
}
