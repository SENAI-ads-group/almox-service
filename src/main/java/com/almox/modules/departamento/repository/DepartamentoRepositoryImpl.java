package com.almox.modules.departamento.repository;

import com.almox.core.repository.SelectBuilder;
import com.almox.modules.departamento.model.Departamento;
import com.almox.modules.departamento.model.FiltroDepartamentoDTO;
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
