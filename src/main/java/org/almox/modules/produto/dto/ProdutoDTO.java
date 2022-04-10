package org.almox.modules.produto.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.almox.modules.common.DTOSimples;
import org.almox.modules.produto.model.UnidadeMedida;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Schema(name = "Produto", description = "Dados de produtos")
public class ProdutoDTO {
    public UUID id;
    public String descricao;
    public String codigoBarras;
    public Set<ProdutoFornecedor> fornecedores;
    public UnidadeMedida unidadeMedida;
    public String detalhes;
    public Set<String> palavrasChave;
    public Set<DTOSimples> departamentos;
    public DTOSimples grupo;
    public BigDecimal estoqueAtual;
    public BigDecimal estoqueMaximo;
    public boolean controlaEstoqueMinimo;
    public boolean controlaEstoqueMaximo;

    public static class ProdutoFornecedor extends DTOSimples {
        public DTOSimples pessoa;
    }
}
