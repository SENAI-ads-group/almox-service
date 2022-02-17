package com.almox.modules.fabricante.repository;
import com.almox.core.repository.SelectBuilder;
import com.almox.modules.fabricante.model.Fabricante;
import com.almox.modules.fabricante.model.FiltroFabricanteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class FabricanteRepositoryImpl implements FabricanteRepositoryCustom {
    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public FabricanteRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Fabricante> findAll(FiltroFabricanteDTO filtro) {
        var query = new SelectBuilder(Fabricante.class)
                .like("cnpj", filtro.getCnpj())
                .like("nomeFantasia", filtro.getNomeFantasia())
                .like("razaoSocial", filtro.getRazaoSocial())
                .filtrarStatusAuditavel(filtro.getFiltroStatusAuditavel())
                .criarQuery(entityManager, Fabricante.class);
        return query.getResultList();

    }
}
