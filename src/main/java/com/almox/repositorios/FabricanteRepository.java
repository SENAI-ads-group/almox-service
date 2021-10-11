package com.almox.repositorios;

import com.almox.model.entidades.Fabricante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FabricanteRepository extends JpaRepository <Fabricante, Long> {
}
