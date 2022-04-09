package org.almox.modules.produto.model;

import org.almox.modules.departamento.model.Departamento;
import org.almox.modules.fornecedor.model.Fornecedor;
import org.almox.modules.grupo.model.Grupo;
import org.almox.modules.auditoria.FiltroStatusAuditavel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class FiltroProduto implements Serializable {
    private String descricao;
    private String codigoBarras;
    private List<Grupo> grupos;
    private List<Departamento> departamentos;
    private Fornecedor fornecedor;
    private UnidadeMedida unidadeMedida;
    private FiltroStatusAuditavel filtroStatusAuditavel;
}
