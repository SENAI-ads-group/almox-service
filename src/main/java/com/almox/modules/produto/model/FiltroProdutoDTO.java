package com.almox.modules.produto.model;

import com.almox.modules.departamento.model.Departamento;
import com.almox.modules.fornecedor.model.Fornecedor;
import com.almox.modules.grupo.model.Grupo;
import com.almox.modules.auditoria.FiltroStatusAuditavel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class FiltroProdutoDTO implements Serializable {
    private String descricao;
    private String codigoBarras;
    private List<Grupo> grupos;
    private List<Departamento> departamentos;
    private Fornecedor fornecedor;
    private UnidadeMedida unidadeMedida;
    private FiltroStatusAuditavel filtroStatusAuditavel;
}
