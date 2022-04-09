package org.almox.modules.pedido.repository;

import org.almox.modules.pedido.model.FiltroPedidoDTO;
import org.almox.modules.pedido.model.Pedido;

import java.util.List;

public interface PedidoRepositoryCustom {
    List<Pedido> findAll(FiltroPedidoDTO filtro);
}
