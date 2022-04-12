package org.almox.modules.requisicao.service;

import lombok.RequiredArgsConstructor;
import org.almox.core.config.validation.ValidatorAutoThrow;
import org.almox.core.exceptions.EntidadeNaoEncontradaException;
import org.almox.modules.operador.model.Operador;
import org.almox.modules.requisicao.dto.FiltroRequisicao;
import org.almox.modules.requisicao.model.Requisicao;
import org.almox.modules.requisicao.repository.RequisicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RequisicaoServiceImpl implements RequisicaoService {

    private final RequisicaoRepository repository;
    private final ValidatorAutoThrow validator;
    private final Operador operadorLogado;

    @Override
    public Requisicao criar(Requisicao requisicao) {
        validator.validate(requisicao);
        return repository.save(requisicao);
    }

    @Override
    public Requisicao buscarPorId(UUID id) {
        Requisicao requisicaoEncontrada = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("${requisicao_nao_encontrada}"));
        return requisicaoEncontrada;
    }

    @Override
    public List<Requisicao> buscar(FiltroRequisicao filtro, Sort sort) {
        return repository.buscar(filtro.status, sort);
    }

    @Override
    public Page<Requisicao> buscarPaginado(FiltroRequisicao filtro, Pageable pageable) {
        return repository.buscar(filtro.status, pageable);
    }

    @Override
    public Requisicao atualizar(UUID id, Requisicao requisicao) {
        buscarPorId(id);
        validator.validate(requisicao);
        requisicao.setId(id);
        Requisicao requisicaoAtualizada = repository.save(requisicao);
        return requisicaoAtualizada;
    }

    @Override
    public void excluir(UUID id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}
