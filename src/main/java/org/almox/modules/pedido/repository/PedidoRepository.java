package org.almox.modules.pedido.repository;

import org.almox.modules.pedido.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID>, PedidoRepositoryCustom {
}
