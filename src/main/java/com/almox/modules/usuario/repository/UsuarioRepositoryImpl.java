package com.almox.modules.usuario.repository;

import com.almox.core.repository.SelectBuilder;
import com.almox.modules.usuario.model.FiltroUsuarioDTO;
import com.almox.modules.usuario.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
