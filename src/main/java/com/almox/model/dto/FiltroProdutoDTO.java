package com.almox.model.dto;

import com.almox.model.entidades.Departamento;
import com.almox.model.enums.FiltroStatusAuditavel;
import com.almox.model.enums.UnidadeMedida;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class FiltroProdutoDTO implements Serializable {
    private String descricao;
    private String codigoBarras;
    private List<GrupoDTO> grupos;
    private List<Departamento> departamentos;
    //private FornecedorDTO fornecedor;
    private UnidadeMedida unidadeMedida;
    private FiltroStatusAuditavel filtroStatusAuditavel;
}
