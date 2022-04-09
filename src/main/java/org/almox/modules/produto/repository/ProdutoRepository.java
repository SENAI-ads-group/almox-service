package org.almox.modules.produto.repository;

import org.almox.modules.produto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    Optional<Produto> findByCodigoBarras(String codigoBarras);

    List<Produto> findAllByDescricao(String descricao);
}
