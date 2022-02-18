package com.almox.modules.fornecedor.repository;

import com.almox.core.repository.SelectBuilder;
import com.almox.modules.fornecedor.model.FiltroFornecedorDTO;
import com.almox.modules.fornecedor.model.Fornecedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class FornecedorRepositoryImpl implements FornecedorRepositoryCustom {
    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public FornecedorRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Fornecedor> findAll(FiltroFornecedorDTO filtro) {
        var query = new SelectBuilder(Fornecedor.class)
                .like("cnpj", filtro.getCnpj())
                .like("nomeFantasia", filtro.getNomeFantasia())
                .like("razaoSocial", filtro.getRazaoSocial())
                .filtrarStatusAuditavel(filtro.getFiltroStatusAuditavel())
                .criarQuery(entityManager, Fornecedor.class);
        return query.getResultList();

    }
}
