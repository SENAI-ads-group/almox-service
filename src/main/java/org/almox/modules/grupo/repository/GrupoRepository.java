package org.almox.modules.grupo.repository;

import org.almox.modules.grupo.model.Grupo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.UUID;

@ApplicationScope
@Repository
public interface GrupoRepository extends JpaRepository<Grupo, UUID> {

    @Query("FROM Grupo as g WHERE LOWER(g.descricao) LIKE CONCAT('%', TRIM(LOWER(:descricao)), '%')")
    Page<Grupo> buscarPorDescricao(@Param("descricao") String descricao, Pageable pageable);

    @Query("FROM Grupo as g WHERE g.dataExclusao IS NULL AND LOWER(g.descricao) LIKE CONCAT('%', TRIM(LOWER(:descricao)), '%')")
    Page<Grupo> buscarAtivosPorDescricao(@Param("descricao") String descricao, Pageable pageable);

    @Query("FROM Grupo as g WHERE g.dataExclusao IS NOT NULL AND LOWER(g.descricao) LIKE CONCAT('%', TRIM(LOWER(:descricao)), '%')")
    Page<Grupo> buscarExcluidosPorDescricao(@Param("descricao") String descricao, Pageable pageable);
}
