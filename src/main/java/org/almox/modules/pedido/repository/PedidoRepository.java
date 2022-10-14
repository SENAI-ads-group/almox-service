package org.almox.modules.pedido.repository;

import org.almox.modules.pedido.model.Pedido;
import org.almox.modules.pedido.model.StatusPedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
    @Query("FROM Pedido as ped WHERE ped.status = :status")
    List<Pedido> buscar(@Param("status") StatusPedido status, Sort sort);

    @Query("FROM Pedido as ped WHERE ped.status = COALESCE(CAST( :status AS string) , ped.status) ")
    Page<Pedido> buscar(@Param("status") String status, Pageable pageable);

    @Query("FROM Pedido as ped WHERE ped.comprador.id = :idOperador AND ped.status = COALESCE(CAST( :status AS string) , ped.status) ")
    Page<Pedido> buscar(@Param("status") String status, @Param("idOperador") UUID idOperador, Pageable pageable);
}
