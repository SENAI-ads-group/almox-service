package org.almox.modules.grupo.service;

import lombok.RequiredArgsConstructor;
import org.almox.core.exceptions.EntidadeNaoEncontradaException;
import org.almox.core.validation.ValidatorAutoThrow;
import org.almox.modules.grupo.model.FiltroGrupo;
import org.almox.modules.grupo.model.Grupo;
import org.almox.modules.grupo.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GrupoServiceImpl implements GrupoService {

    private final GrupoRepository repository;
    private final ValidatorAutoThrow validator;

    @Override
    public Grupo criar(Grupo grupo) {
        validator.validate(grupo);
        grupo.setDataCriacao(LocalDateTime.now());
        return repository.save(grupo);
    }

    @Override
    public Grupo buscarPorId(UUID id) {
        Grupo grupoEncontrado = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("${grupo_nao_encontrado}"));
        return grupoEncontrado;
    }

    @Override
    public List<Grupo> buscar(FiltroGrupo filtro, Sort sort) {
        return repository.buscarPorDescricao(filtro.descricao, sort);
    }

    @Override
    public Page<Grupo> buscarPaginado(FiltroGrupo filtro, Pageable pageable) {
        return repository.buscarPorDescricao(filtro.descricao, pageable);
    }

    @Override
    public Grupo atualizar(UUID id, Grupo grupo) {
        buscarPorId(id);
        validator.validate(grupo);
        grupo.setId(id);
        Grupo grupoAtualizado = repository.save(grupo);
        return grupoAtualizado;
    }

    @Override
    public void excluir(UUID id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}
