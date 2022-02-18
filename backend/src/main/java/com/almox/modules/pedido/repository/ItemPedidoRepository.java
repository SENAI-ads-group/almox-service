package com.almox.modules.pedido.repository;

import com.almox.modules.crud.CrudRepository;
import com.almox.modules.pedido.model.ItemPedido;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends CrudRepository<ItemPedido> {
}
