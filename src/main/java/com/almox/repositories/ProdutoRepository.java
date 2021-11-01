package com.almox.repositories;

import com.almox.model.entidades.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>, ProdutoRepositoryCustom {

    List<Produto> findAllByCodigoBarras(String codigoBarras);

    List<Produto> findAllByDescricao(String descricao);
}
