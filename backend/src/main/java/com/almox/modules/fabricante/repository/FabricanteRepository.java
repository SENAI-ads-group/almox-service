package com.almox.modules.fabricante.repository;

import com.almox.modules.crud.CrudRepository;
import com.almox.modules.fabricante.model.Fabricante;
import org.springframework.stereotype.Repository;

@Repository
public interface FabricanteRepository extends CrudRepository<Fabricante>, FabricanteRepositoryCustom {

}
