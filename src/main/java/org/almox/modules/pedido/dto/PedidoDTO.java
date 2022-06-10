package org.almox.modules.pedido.dto;

import org.almox.modules.common.DTOSimples;
import org.almox.modules.pedido.model.StatusPedido;
import org.almox.modules.produto.model.UnidadeMedida;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class PedidoDTO {
    public UUID id;
    public StatusPedido status;
    public LocalDateTime dataPedido;
    public LocalDateTime dataPrevisaoEntrega;
    public LocalDateTime dataEntrega;
    public CompradorPedido comprador;
    public FornecedorPedido fornecedor;
    public List<ItemPedidoDTO> itens;

    public static class CompradorPedido extends DTOSimples {
        public PessoaPedido pessoa;
    }

    public static class FornecedorPedido extends DTOSimples {
        public PessoaPedido pessoa;
    }

    public static class PessoaPedido extends DTOSimples {
        public String nome;
        public String cpf;
        public String email;
    }

    public static class ItemPedidoDTO extends DTOSimples {
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
