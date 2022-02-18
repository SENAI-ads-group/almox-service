package com.almox.modules.produto.repository;

import com.almox.core.repository.SelectBuilder;
import com.almox.modules.produto.model.FiltroProdutoDTO;
import com.almox.modules.produto.model.Produto;
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
