package com.almox.repositories.impl;

import com.almox.model.dto.FiltroGrupoDTO;
import com.almox.model.entidades.Grupo;
import com.almox.repositories.GrupoRepositoryCustom;
import com.almox.repositories.util.SelectBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class GrupoRepositoryImpl implements GrupoRepositoryCustom {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    private GrupoRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Grupo> findAll(FiltroGrupoDTO filtro) {
        var query = new SelectBuilder(Grupo.class)
                .likeIgnoreCase("descricao", filtro.getDescricao())
                .criarQuery(entityManager, Grupo.class);
        return query.getResultList();
    }
}
