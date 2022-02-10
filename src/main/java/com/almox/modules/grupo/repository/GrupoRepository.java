package com.almox.modules.grupo.repository;

import com.almox.modules.crud.CrudRepository;
import com.almox.modules.grupo.model.Grupo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoRepository extends CrudRepository<Grupo>, GrupoRepositoryCustom {

    List<Grupo> findAllByDescricao(String descricao);

}
