package com.almox.repositories.requisicao;

import com.almox.model.dto.FiltroRequisicaoDTO;
import com.almox.model.entidades.Requisicao;
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
        return null;
    }
}
