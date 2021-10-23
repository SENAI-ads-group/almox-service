package com.almox.repositories.impl;

import com.almox.model.dto.FiltroDepartamentoDTO;
import com.almox.model.entidades.Departamento;
import com.almox.repositories.DepartamentoRepositoryCustom;
import com.almox.repositories.util.SelectBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DepartamentoRepositoryImpl implements DepartamentoRepositoryCustom {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public DepartamentoRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Departamento> findAll(FiltroDepartamentoDTO filtro) {
        var query = new SelectBuilder(Departamento.class)
                .likeIgnoreCase("nome", filtro.getNome())
                .filtrarStatusAuditavel(filtro.getFiltroStatusAuditavel())
                .criarQuery(entityManager, Departamento.class);
        return query.getResultList();
    }

}
