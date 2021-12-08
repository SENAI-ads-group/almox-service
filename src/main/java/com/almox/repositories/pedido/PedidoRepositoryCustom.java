package com.almox.repositories.pedido;

import com.almox.model.dto.FiltroPedidoDTO;
import com.almox.model.dto.FiltroRequisicaoDTO;
import com.almox.model.entidades.Requisicao;
import com.almox.model.entidades.pedido.Pedido;

import java.util.List;

public interface PedidoRepositoryCustom {
    List<Pedido> findAll(FiltroPedidoDTO filtro);
}
