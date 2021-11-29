package com.almox.repositories.movimento;

import com.almox.model.entidades.Movimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentoRepository extends JpaRepository<Movimento, Long> {
}
