package com.almox.repositorios;

import com.almox.model.entidades.ItemOrcamentoDepartamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemOrcamentoDepartamentoRepository extends JpaRepository<ItemOrcamentoDepartamento, Long> {
}
