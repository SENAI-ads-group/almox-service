package com.almox.repositories.departamento;

import com.almox.model.dto.FiltroDepartamentoDTO;
import com.almox.model.dto.UsuarioDTO;
import com.almox.model.entidades.Departamento;
import com.almox.model.entidades.Fabricante;
import com.almox.model.entidades.Produto;
import com.almox.repositories.departamento.DepartamentoRepositoryCustom;
import com.almox.repositories.produto.ProdutoRepository;
import com.almox.repositories.util.SelectBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

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
