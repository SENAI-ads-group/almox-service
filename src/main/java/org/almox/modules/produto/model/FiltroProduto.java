package org.almox.modules.produto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FiltroProduto implements Serializable {
    public String descricao;
    public String codigoBarras;
    public Set<UUID> idGrupos;
    public Set<UUID> idDepartamentos;
    public UUID idFornecedor;
    public UnidadeMedida unidadeMedida;
}
