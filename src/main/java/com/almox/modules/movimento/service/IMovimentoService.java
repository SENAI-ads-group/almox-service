package com.almox.modules.movimento.service;

import com.almox.modules.movimento.model.Movimento;
import com.almox.modules.requisicao.model.Requisicao;

public interface IMovimentoService {

    Movimento movimentarProdutosRequisicao(Requisicao requisicao);

    // Movimento movimentarProdutosPedidoCompra(PedidoCompra pedidoCompra);
}
