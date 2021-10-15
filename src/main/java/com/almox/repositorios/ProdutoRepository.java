package com.almox.repositorios;

import com.almox.model.entidades.Produto;
import com.almox.model.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findAllByCodigoBarras(String codigoBarras);

    List<Produto> findAllByDescricao(String descricao);

}
