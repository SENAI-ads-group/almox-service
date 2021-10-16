package com.almox.repositorios.impl;

import com.almox.model.dto.FiltroUsuarioDTO;
import com.almox.model.entidades.Usuario;
import com.almox.repositorios.UsuarioRepositoryCustom;
import com.almox.repositorios.util.SelectBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepositoryCustom {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public UsuarioRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Usuario> findAll(FiltroUsuarioDTO filtro) {
        var query = new SelectBuilder(Usuario.class)
                .likeIgnoreCase("nome", filtro.getNome())
                .likeIgnoreCase("email", filtro.getEmail())
                .isEquals("tipoUsuario", filtro.getTipoUsuario())
                .filtrarStatusAuditavel(filtro.getFiltroStatusAuditavel())
                .criarQuery(entityManager, Usuario.class);
        return query.getResultList();
    }

}
