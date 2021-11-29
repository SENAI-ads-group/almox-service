package com.almox.repositories.requisicao;

import com.almox.model.entidades.Requisicao;
import com.almox.repositories.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequisicaoRepository extends CrudRepository<Requisicao>, RequisicaoRepositoryCustom {
}
