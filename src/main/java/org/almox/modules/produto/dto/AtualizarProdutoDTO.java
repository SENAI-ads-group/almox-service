package org.almox.modules.produto.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.almox.modules.common.DTOSimples;
import org.almox.modules.produto.model.UnidadeMedida;

import java.math.BigDecimal;
import java.util.Set;

@Schema(name = "AtualizarProduto", description = "Schema para atualizar um produto")
public class AtualizarProdutoDTO {
    @Schema(required = true)
    public String descricao;
    public String codigoBarras;
    public BigDecimal custoMedio;
    public Set<DTOSimples> fornecedores;
    @Schema(required = true)
    public UnidadeMedida unidadeMedida;
    public String detalhes;
    public Set<String> palavrasChave;
    public Set<DTOSimples> departamentos;
    @Schema(required = true)
    public DTOSimples grupo;
    public BigDecimal estoqueMinimo;
    public BigDecimal estoqueMaximo;
    @Schema(type = "boolean", defaultValue = "false")
    public boolean controlaEstoqueMinimo;
    @Schema(type = "boolean", defaultValue = "false")
    public boolean controlaEstoqueMaximo;
}
