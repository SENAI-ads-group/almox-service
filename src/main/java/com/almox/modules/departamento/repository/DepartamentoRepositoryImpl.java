package com.almox.modules.departamento.repository;

import com.almox.modules.departamento.model.Departamento;
import com.almox.modules.departamento.model.FiltroDepartamentoDTO;
import com.almox.modules.produto.repository.ProdutoRepository;
import com.almox.core.repository.SelectBuilder;
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
    private ProdutoRepository produtoRepository;

    @Autowired
    public DepartamentoRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Departamento> findAll(FiltroDepartamentoDTO filtro) {
        var query = new SelectBuilder(Departamento.class)
                .like("nome", filtro.getNome())
                .filtrarStatusAuditavel(filtro.getFiltroStatusAuditavel())
                .criarQuery(entityManager, Departamento.class);
        return query.getResultList();
    }

//    @Query(value = "SELECT * FROM dpto_departamento JOIN produtos_departamentos pd ON pd.prod_id = ?1", nativeQuery = true)
//    @Override
//    public List<Departamento> findAllByProduct(Long idProduto, boolean relacionados) {
//        var operador = relacionados ? "=" : "<>";
//        final String SQL = "SELECT dpto FROM Departamento dpto JOIN dpto.produtos prod WHERE prod.id " + operador + " :idProduto";
//
//        return entityManager.createQuery(SQL, Departamento.class)
//                .setParameter("idProduto", idProduto)
//                .getResultList();
//    }

}
