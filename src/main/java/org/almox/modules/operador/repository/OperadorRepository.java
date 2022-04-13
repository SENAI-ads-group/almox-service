package org.almox.modules.operador.repository;

import org.almox.modules.operador.model.Operador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OperadorRepository extends JpaRepository<Operador, UUID> {

    Optional<Operador> findByLoginEquals(String login);

    @Query("FROM Operador as ope JOIN PessoaFisica as pf ON pf.id = ope.pessoa.id AND pf.cpf = :cpf")
    Optional<Operador> buscarPorCpfPessoa(@Param("cpf") String cpf);
}
