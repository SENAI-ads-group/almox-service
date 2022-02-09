package com.almox.modules.pedido.repository;

import com.almox.modules.pedido.model.FiltroPedidoDTO;
import com.almox.modules.pedido.model.Pedido;

import java.util.List;

public interface PedidoRepositoryCustom {
    List<Pedido> findAll(FiltroPedidoDTO filtro);
}
