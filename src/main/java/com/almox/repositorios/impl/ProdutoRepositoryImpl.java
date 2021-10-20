package com.almox.repositorios.impl;

import com.almox.model.dto.FiltroProdutoDTO;
import com.almox.model.entidades.Produto;
import com.almox.repositorios.ProdutoRepositoryCustom;
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
         /*var query = new SelectBuilder(Produto.class)
         .likeIgnoreCase("descricao", filtro.getDescricao())
         .likeIgnoreCase("codigoBarras", filtro.getCodigoBarras())
         */
        return null;
    }

}
