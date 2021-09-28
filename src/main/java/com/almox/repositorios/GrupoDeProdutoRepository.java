package com.almox.repositorios;

import com.almox.model.entidades.GrupoDeProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoDeProdutoRepository extends JpaRepository<GrupoDeProduto, Long> {
}
