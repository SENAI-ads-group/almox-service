package org.almox.modules.produto.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.almox.modules.auditoria.AuditavelDTO;
import org.almox.modules.common.DTOSimples;
import org.almox.modules.produto.model.UnidadeMedida;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Schema(name = "Produto", description = "Dados de produtos")
public class ProdutoDTO {
    public UUID id;
    public AuditavelDTO auditoria;
    public String descricao;
    public String codigoBarras;
    public Set<FornecedorProduto> fornecedores;
    public UnidadeMedida unidadeMedida;
    public String detalhes;
    public Set<String> palavrasChave;
    public Set<DepartamentoProduto> departamentos;
    public GrupoProduto grupo;
    public BigDecimal estoqueMinimo;
    public BigDecimal estoqueAtual;
    public BigDecimal estoqueMaximo;
    public boolean controlaEstoqueMinimo;
    public boolean controlaEstoqueMaximo;

    public static class FornecedorProduto extends DTOSimples {
        public PessoaFornecedor pessoa;

        public static class PessoaFornecedor extends DTOSimples {
            public String nome;
        }
    }

    public static class GrupoProduto extends DTOSimples {
        public String descricao;
    }

    public static class DepartamentoProduto extends DTOSimples {
        public String descricao;
    }
}
