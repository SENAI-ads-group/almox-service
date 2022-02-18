package com.almox.modules.pedido.repository;

import com.almox.modules.crud.CrudRepository;
import com.almox.modules.pedido.model.Pedido;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido>, PedidoRepositoryCustom {
}
