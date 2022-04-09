package org.almox.modules.requisicao.repository;

import org.almox.modules.requisicao.model.Requisicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RequisicaoRepository extends JpaRepository<Requisicao, UUID> {
}
