package org.almox.modules.operador.repository;

import org.almox.modules.operador.model.Operador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OperadorRepository extends JpaRepository<Operador, UUID> {

    Optional<Operador> findByLoginEquals(String login);
}
