package org.almox.modules.grupo.repository;

import org.almox.modules.grupo.model.Grupo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, UUID> {

    @Query("FROM Grupo as g WHERE LOWER(g.descricao) LIKE CONCAT(:descricao, '%')")
    List<Grupo> buscarPorDescricao(@Param("descricao") String descricao, Sort sort);

    @Query("FROM Grupo as g WHERE LOWER(g.descricao) LIKE CONCAT(:descricao, '%')")
    Page<Grupo> buscarPorDescricao(@Param("descricao") String descricao, Pageable pageable);
}
