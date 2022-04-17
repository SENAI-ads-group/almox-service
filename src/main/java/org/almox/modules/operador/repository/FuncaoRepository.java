package org.almox.modules.operador.repository;

import org.almox.modules.operador.model.Funcao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FuncaoRepository extends JpaRepository<Funcao, UUID> {

    @Query("FROM Funcao AS func WHERE func.nome = 'ROLE_ADMIN'")
    Funcao buscarFuncaoAdministrador();

    @Query("FROM Funcao AS func WHERE func.nome = 'ROLE_ADMIN'")
    Funcao buscarFuncaoAlmoxarife();

    @Query("FROM Funcao AS func WHERE func.nome = :nome")
    Optional<Funcao> buscarPorNome(@Param("nome") String nome);
}
