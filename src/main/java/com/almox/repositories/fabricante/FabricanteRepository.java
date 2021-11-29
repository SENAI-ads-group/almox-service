package com.almox.repositories.fabricante;

import com.almox.model.entidades.Fabricante;
import com.almox.repositories.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FabricanteRepository extends CrudRepository<Fabricante>, FabricanteRepositoryCustom {


}
