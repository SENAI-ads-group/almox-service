package org.almox.modules.pedido.repository;

import org.almox.core.repository.SelectBuilder;
import org.almox.modules.pedido.model.FiltroPedidoDTO;
import org.almox.modules.pedido.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PedidoRepositoryImpl implements PedidoRepositoryCustom {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public PedidoRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Pedido> findAll(FiltroPedidoDTO filtro) {
        var query = new SelectBuilder(Pedido.class)
                .isEquals("status", filtro.getStatus())
                .criarQuery(entityManager, Pedido.class);
        return query.getResultList();
    }
}
