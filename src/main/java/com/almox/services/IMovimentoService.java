package com.almox.services;

import com.almox.model.entidades.Movimento;
import com.almox.model.entidades.Requisicao;

public interface IMovimentoService {

    Movimento movimentarProdutosRequisicao(Requisicao requisicao);

    // Movimento movimentarProdutosPedidoCompra(PedidoCompra pedidoCompra);
}
