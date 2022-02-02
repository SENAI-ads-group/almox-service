package com.almox.modules.produto.repository;

import com.almox.modules.produto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>, ProdutoRepositoryCustom {

    List<Produto> findAllByCodigoBarras(String codigoBarras);

    List<Produto> findAllByDescricao(String descricao);
}
