package com.almox.repositories.pedido;

import com.almox.model.entidades.pedido.ItemPedido;
import com.almox.repositories.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends CrudRepository<ItemPedido> {
}
