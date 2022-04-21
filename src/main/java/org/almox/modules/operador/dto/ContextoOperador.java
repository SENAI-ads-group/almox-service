package org.almox.modules.operador.dto;

import org.almox.modules.operador.model.Operador;

import java.util.Optional;

public interface ContextoOperador {
    Optional<Operador> getOperadorLogado();
}
