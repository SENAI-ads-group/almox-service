package com.almox.modules.grupo.repository;

import com.almox.core.repository.SelectBuilder;
import com.almox.modules.grupo.model.FiltroGrupoDTO;
import com.almox.modules.grupo.model.Grupo;
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
