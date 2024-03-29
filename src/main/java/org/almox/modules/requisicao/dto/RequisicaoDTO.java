package org.almox.modules.requisicao.dto;

import org.almox.modules.common.DTOSimples;
import org.almox.modules.produto.model.UnidadeMedida;
import org.almox.modules.requisicao.model.StatusRequisicao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class RequisicaoDTO {
    public UUID id;
    public StatusRequisicao status;
    public String codigoConfirmacao;
    public DepartamentoRequisicao departamento;
    public LocalDateTime dataRequisicao;
    public LocalDateTime dataEntrega;
    public AlmoxarifeRequisitanteRequisicao requisitante;
    public AlmoxarifeRequisitanteRequisicao almoxarife;
    public List<ItemRequisicaoDTO> itens;

    public static class DepartamentoRequisicao extends DTOSimples {
        public String descricao;
    }

    public static class AlmoxarifeRequisitanteRequisicao extends DTOSimples {
        public PessoaRequisicao pessoa;
    }

    public static class PessoaRequisicao extends DTOSimples {
        public String nome;
        public String cpf;
        public String email;
    }

    public static class ItemRequisicaoDTO extends DTOSimples {
        public ItemProduto produto;
        public BigDecimal quantidade;
    }

    public static class ItemProduto extends DTOSimples {
        public String descricao;
        public String codigoBarras;
        public UnidadeMedida unidadeMedida;
        public ItemProdutoGrupo grupo;
    }

    public static class  ItemProdutoGrupo extends DTOSimples{
        public String descricao;
    }
}
