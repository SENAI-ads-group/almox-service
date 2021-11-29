package com.almox.repositories.grupo;

import com.almox.model.entidades.Grupo;
import com.almox.repositories.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoRepository extends CrudRepository<Grupo>, GrupoRepositoryCustom {

    List<Grupo> findAllByDescricao(String descricao);

}
