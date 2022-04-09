package org.almox.modules.produto.repository;

import org.almox.modules.produto.model.Produto;
import org.almox.modules.produto.model.UnidadeMedida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    Optional<Produto> findByCodigoBarras(String codigoBarras);

    List<Produto> findAllByDescricao(String descricao);

    @Query("FROM Produto as prod                                            \n" +
            "JOIN Grupo as grp ON                                           \n" +
            "   grp.id IN :idGrupos                                         \n" +
            "JOIN Departamento as dpto ON                                   \n" +
            "   dpto.id IN :idDepartamentos                                 \n" +
            "   AND dpto IN ELEMENTS(prod.departamentos)                    \n" +
            "JOIN Fornecedor as forn ON                                     \n" +
            "   forn.id = :idFornecedor                                     \n" +
            "WHERE                                                          \n" +
            "   LOWER(prod.descricao) LIKE CONCAT('%', :descricao ,'%')     \n" +
            "   AND prod.codigoBarras = :codigoBarras                       \n" +
            "   AND prod.unidadeMedida = :unidadeMedida                     \n"
    )
    List<Produto> buscar(
            @Param("descricao") String descricao,
            @Param("codigoBarras") String codigoBarras,
            @Param("idGrupos") Set<UUID> idGrupos,
            @Param("idDepartamentos") Set<UUID> idDepartamentos,
            @Param("idFornecedor") UUID idFornecedor,
            @Param("unidadeMedida") UnidadeMedida unidadeMedida,
            Sort sort
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
            "   LOWER(prod.descricao) LIKE CONCAT('%', :descricao ,'%')     \n" +
            "   AND prod.codigoBarras = :codigoBarras                       \n" +
            "   AND prod.unidadeMedida = :unidadeMedida                     \n"
    )
    Page<Produto> buscar(
            @Param("descricao") String descricao,
            @Param("codigoBarras") String codigoBarras,
            @Param("idGrupos") Set<UUID> idGrupos,
            @Param("idDepartamentos") Set<UUID> idDepartamentos,
            @Param("idFornecedor") UUID idFornecedor,
            @Param("unidadeMedida") UnidadeMedida unidadeMedida,
            Pageable pageable
    );
}
