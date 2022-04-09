package org.almox.modules.produto.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.almox.modules.produto.model.UnidadeMedida;

import java.util.Set;
import java.util.UUID;

public class CriarProduto {
    public String descricao;
    public String codigoBarras;
    public UnidadeMedida unidadeMedida;
    public String detalhes;
    public Set<String> palavrasChave;
    @Schema(required = true)
    public UUID idGrupo;
}
