package com.almox.repositories.departamento;

import com.almox.model.dto.FiltroDepartamentoDTO;
import com.almox.model.dto.UsuarioDTO;
import com.almox.model.entidades.Departamento;
import com.almox.model.entidades.Produto;
import com.almox.repositories.departamento.DepartamentoRepositoryCustom;
import com.almox.repositories.produto.ProdutoRepository;
import com.almox.repositories.util.SelectBuilder;
import org.springframework.beans.factory.annotation.Autowired;
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
        final String SQL = "SELECT dpto FROM Departamento dpto "
                + (filtro.getIdProduto() != null ? "LEFT JOIN Produto p ON p.id <> :idProduto " : "")
                + "WHERE LOWER(dpto.nome) LIKE LOWER(CONCAT('%', :nome,'%'))";
        var query = entityManager.createQuery(SQL, Departamento.class)
                .setParameter("idProduto", filtro.getIdProduto())
                .setParameter("nome", filtro.getNome() == null ? "" : filtro.getNome());
        return query.getResultList();
    }

}
