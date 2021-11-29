package com.almox.repositories.produto;

import com.almox.model.entidades.Produto;
import com.almox.repositories.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto>, ProdutoRepositoryCustom {

    List<Produto> findAllByCodigoBarras(String codigoBarras);

    List<Produto> findAllByDescricao(String descricao);
}
