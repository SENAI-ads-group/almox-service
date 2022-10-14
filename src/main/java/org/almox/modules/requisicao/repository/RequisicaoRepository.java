package org.almox.modules.requisicao.repository;

import org.almox.modules.requisicao.model.Requisicao;
import org.almox.modules.requisicao.model.StatusRequisicao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RequisicaoRepository extends JpaRepository<Requisicao, UUID> {

    @Query("FROM Requisicao as req WHERE req.codigoConfirmacao = :codigoConfirmacao")
    Optional<Requisicao> buscarPorCodigoConfirmacao(@Param("codigoConfirmacao") String codigoConfirmacao);

    @Query("FROM Requisicao as req WHERE req.status = :status")
    List<Requisicao> buscar(@Param("status") StatusRequisicao status, Sort sort);

    @Query("FROM Requisicao as req WHERE req.status = COALESCE(CAST( :status AS string) , req.status) ")
    Page<Requisicao> buscar(@Param("status") String status, Pageable pageable);

    @Query("FROM Requisicao as req WHERE req.almoxarife.id = :idOperador OR req.requisitante.id = :idOperador AND req.status = COALESCE(CAST( :status AS string) , req.status) ")
    Page<Requisicao> buscar(@Param("status") String status, @Param("idOperador") UUID idOperador, Pageable pageable);
}
