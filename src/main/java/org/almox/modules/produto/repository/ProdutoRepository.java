package org.almox.modules.produto.repository;

import org.almox.modules.produto.model.Produto;
import org.almox.modules.produto.model.UnidadeMedida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    @Query("FROM Produto as prod                                            \n" +
            "WHERE                                                          \n" +
            "   prod.dataExclusao IS NULL                                   \n" +
            "   OR LOWER(prod.descricao) LIKE CONCAT('%', TRIM(LOWER(:descricao)) ,'%') \n" +
            "   OR prod.codigoBarras = :codigoBarras                       \n" +
            "   OR prod.unidadeMedida = :unidadeMedida                     \n"
    )
    Page<Produto> buscarAtivos(
            @Param("descricao") String descricao,
            @Param("codigoBarras") String codigoBarras,
            @Param("unidadeMedida") UnidadeMedida unidadeMedida,
            Pageable pageable
    );

    @Query("FROM Produto as prod                                            \n" +
            "JOIN Grupo as grp ON                                           \n" +
            "   grp.id IN :idGrupos                                         \n" +
            "JOIN Departamento as dpto ON                                   \n" +
            "   dpto.id IN :idDepartamentos                                 \n" +
            "   AND dpto IN ELEMENTS(prod.departamentos)                    \n" +
            "JOIN Fornecedor as forn ON                                     \n" +
            "   forn.id = :idFornecedor                                     \n" +
            "WHERE                                                          \n" +
            "   prod.dataExclusao IS NOT NULL                               \n" +
            "   AND LOWER(prod.descricao) LIKE CONCAT('%', :descricao ,'%') \n" +
            "   AND prod.codigoBarras = :codigoBarras                       \n" +
            "   AND prod.unidadeMedida = :unidadeMedida                     \n"
    )
    Page<Produto> buscarExcluidos(
            @Param("descricao") String descricao,
            @Param("codigoBarras") String codigoBarras,
            @Param("idGrupos") Set<UUID> idGrupos,
            @Param("idDepartamentos") Set<UUID> idDepartamentos,
            @Param("idFornecedor") UUID idFornecedor,
            @Param("unidadeMedida") UnidadeMedida unidadeMedida,
            Pageable pageable
    );

    @Query(nativeQuery = true, value = "SELECT PROD_COD_BARRAS FROM PROD_PRODUTO WHERE PROD_COD_BARRAS = :codigoBarras LIMIT 1")
    Optional<String> buscarCodigoBarras(@Param("codigoBarras") String codigoBarras);
}
