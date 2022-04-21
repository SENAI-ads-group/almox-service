package com.almox.modules.requisicao.repository;

import com.almox.core.repository.SelectBuilder;
import com.almox.modules.requisicao.model.FiltroRequisicaoDTO;
import com.almox.modules.requisicao.model.Requisicao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RequisicaoRepositoryImpl implements RequisicaoRepositoryCustom {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public RequisicaoRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Requisicao> findAll(FiltroRequisicaoDTO filtro) {
        var query = new SelectBuilder(Requisicao.class)
                .isEquals("status", filtro.getStatus())
                .criarQuery(entityManager, Requisicao.class);
        return query.getResultList();
    }
}
