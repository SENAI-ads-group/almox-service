package com.almox.modules.produto.repository;

import com.almox.modules.crud.CrudRepository;
import com.almox.modules.produto.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto>, ProdutoRepositoryCustom {

    Optional<Produto> findByCodigoBarras(String codigoBarras);

    List<Produto> findAllByDescricao(String descricao);
}
