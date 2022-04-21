package org.almox.modules.operador.service;

import org.almox.modules.operador.dto.FiltroOperador;
import org.almox.modules.operador.model.Operador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface OperadorService {

    Operador criar(Operador operador);

    Operador buscarPorId(UUID id);

    Operador buscarPorLogin(String login);

    Optional<Operador> buscarPorLoginOptional(String login);

    Optional<Operador> buscarPorCpfPessoaOptional(String cpf);

    Page<Operador> buscar(FiltroOperador filtro, Pageable paginacao);

    Operador atualizar(UUID id, Operador operador);

    void excluir(UUID id);

    boolean isAdministrador(Operador operador);

    boolean isAlmoxarife(Operador operador);
}
