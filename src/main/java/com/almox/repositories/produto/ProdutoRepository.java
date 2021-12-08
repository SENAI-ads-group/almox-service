package com.almox.repositories.produto;

import com.almox.model.entidades.Produto;
import com.almox.repositories.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto>, ProdutoRepositoryCustom {

    Optional<Produto> findByCodigoBarras(String codigoBarras);

    List<Produto> findAllByDescricao(String descricao);
}
