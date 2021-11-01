package com.almox.repositories.impl;

import com.almox.model.dto.FiltroProdutoDTO;
import com.almox.model.entidades.Produto;
import com.almox.repositories.ProdutoRepositoryCustom;
import com.almox.repositories.util.SelectBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryCustom {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public ProdutoRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Produto> findAll(FiltroProdutoDTO filtro) {
        var query = new SelectBuilder(Produto.class)
                .likeIgnoreCase("descricao", filtro.getDescricao())
                .likeIgnoreCase("codigoBarras", filtro.getCodigoBarras())
                .in("grupo", filtro.getGrupos())
                .in("departamentos", filtro.getDepartamentos())
                .isEquals("fornecedor", filtro.getFornecedor())
                .isEquals("unidadeMedida", filtro.getUnidadeMedida())
                .filtrarStatusAuditavel(filtro.getFiltroStatusAuditavel())
                .criarQuery(entityManager, Produto.class);
        return query.getResultList();
    }

}
