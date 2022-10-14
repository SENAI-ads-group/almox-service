package org.almox.modules.produto.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.almox.modules.produto.model.UnidadeMedida;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Schema(name = "CriarProduto", description = "Schema para criar um novo produto")
public class CriarProdutoDTO {
    @Schema(required = true)
    public String descricao;
    public String codigoBarras;
    public Set<UUID> idFornecedores;
    @Schema(required = true)
    public UnidadeMedida unidadeMedida;
    public String detalhes;
    public Set<String> palavrasChave;
    public Set<UUID> idDepartamentos;
    @Schema(required = true)
    public UUID idGrupo;
    public BigDecimal estoqueMinimo;
    public BigDecimal estoqueMaximo;
    @Schema(type = "boolean", defaultValue = "false")
    public boolean controlaEstoqueMinimo;
    @Schema(type = "boolean", defaultValue = "false")
    public boolean controlaEstoqueMaximo;
}
