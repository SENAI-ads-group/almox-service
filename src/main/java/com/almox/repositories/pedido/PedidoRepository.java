package com.almox.repositories.pedido;

import com.almox.model.entidades.pedido.Pedido;
import com.almox.repositories.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido>, PedidoRepositoryCustom {
}
