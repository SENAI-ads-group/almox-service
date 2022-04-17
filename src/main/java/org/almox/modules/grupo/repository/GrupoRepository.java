package org.almox.modules.grupo.repository;

import org.almox.modules.departamento.model.Departamento;
import org.almox.modules.grupo.model.Grupo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;
import java.util.UUID;

@ApplicationScope
@Repository
public interface GrupoRepository extends JpaRepository<Grupo, UUID> {

    String QUERY_TODOS_POR_DESCRICAO = "FROM Grupo as g WHERE LOWER(g.descricao) LIKE CONCAT('%', TRIM(LOWER(:descricao)), '%')";
    String QUERY_INATIVOS_POR_DESCRICAO = "FROM Grupo as g WHERE g.dataExclusao IS NOT NULL AND LOWER(g.descricao) LIKE CONCAT('%', TRIM(LOWER(:descricao)), '%')";
    String QUERY_ATIVOS_POR_DESCRICAO = "FROM Grupo as g WHERE g.dataExclusao IS NULL AND LOWER(g.descricao) LIKE CONCAT('%', TRIM(LOWER(:descricao)), '%')";


    @Query(QUERY_TODOS_POR_DESCRICAO)
    List<Grupo> buscarPorDescricao(@Param("descricao") String descricao, Sort sort);

    @Query(QUERY_TODOS_POR_DESCRICAO)
    Page<Grupo> buscarPorDescricao(@Param("descricao") String descricao, Pageable pageable);

    @Query("FROM Grupo as g WHERE g.dataExclusao IS NULL AND LOWER(g.descricao) LIKE CONCAT('%', TRIM(LOWER(:descricao)), '%')")
    List<Grupo> buscarAtivosPorDescricao(@Param("descricao") String descricao, Sort sort);

    @Query(QUERY_ATIVOS_POR_DESCRICAO)
    Page<Grupo> buscarAtivosPorDescricao(@Param("descricao") String descricao, Pageable pageable);

    @Query(QUERY_INATIVOS_POR_DESCRICAO)
    List<Grupo> buscarExcluidosPorDescricao(@Param("descricao") String descricao, Sort sort);

    @Query(QUERY_INATIVOS_POR_DESCRICAO)
    Page<Grupo> buscarExcluidosPorDescricao(@Param("descricao") String descricao, Pageable pageable);
}
