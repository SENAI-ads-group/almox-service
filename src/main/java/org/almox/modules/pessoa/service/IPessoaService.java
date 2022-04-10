package org.almox.modules.pessoa.service;

import org.almox.modules.pessoa.dto.PessoaFiltroDTO;
import org.almox.modules.pessoa.model.Pessoa;
import org.almox.modules.pessoa.model.PessoaFisica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPessoaService {
    <P extends Pessoa> P criar(P pessoa);

    Pessoa buscarPorId(UUID id);

    Optional<Pessoa> buscarPorIdOptional(UUID id);

    Pessoa buscarPorEmail(String email);

    List<Pessoa> buscar(PessoaFiltroDTO filtro, Sort sort);

    Page<Pessoa> buscarPaginado(PessoaFiltroDTO filtro, Pageable pageable);

    <P extends Pessoa> P atualizar(UUID id, P pessoa);

    void excluir(UUID id);
}
