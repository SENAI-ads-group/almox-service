package org.almox.modules.produto.model;

import org.almox.modules.auditoria.FiltroStatusAuditavel;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class FiltroProduto implements Serializable {
    public String descricao;
    public String codigoBarras;
    public Set<UUID> idGrupos;
    public Set<UUID> idDepartamentos;
    public UUID idFornecedor;
    public UnidadeMedida unidadeMedida;
    public FiltroStatusAuditavel status;
}
