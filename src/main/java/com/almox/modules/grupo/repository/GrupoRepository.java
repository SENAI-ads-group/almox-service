package com.almox.modules.grupo.repository;

import com.almox.modules.grupo.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long>, GrupoRepositoryCustom {

    List<Grupo> findAllByDescricao(String descricao);

}
