package com.almox.modules.fabricante.repository;

import com.almox.modules.fabricante.model.Fabricante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FabricanteRepository extends JpaRepository <Fabricante, Long> , FabricanteRepositoryCustom {


}
