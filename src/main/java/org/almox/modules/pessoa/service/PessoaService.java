package org.almox.modules.pessoa.service;

import org.almox.modules.pessoa.dto.FiltroPessoa;
import org.almox.modules.pessoa.model.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface PessoaService {
    <P extends Pessoa> P criar(P pessoa);

    Pessoa buscarPorId(UUID id);

    Optional<Pessoa> buscarPorIdOptional(UUID id);

    Pessoa buscarPorEmail(String email);

    Page<Pessoa> buscar(FiltroPessoa filtro, Pageable paginacao);

    <P extends Pessoa> P atualizar(UUID id, P pessoa);

    void excluir(UUID id);
}
