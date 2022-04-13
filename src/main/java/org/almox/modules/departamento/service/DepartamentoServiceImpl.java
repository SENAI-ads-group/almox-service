package org.almox.modules.departamento.service;

import lombok.RequiredArgsConstructor;
import org.almox.core.config.validation.ValidatorAutoThrow;
import org.almox.core.exceptions.EntidadeNaoEncontradaException;
import org.almox.core.exceptions.ForbidenException;
import org.almox.modules.departamento.model.Departamento;
import org.almox.modules.departamento.model.FiltroDepartamento;
import org.almox.modules.departamento.repository.DepartamentoRepository;
import org.almox.modules.operador.model.Operador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DepartamentoServiceImpl implements DepartamentoService {

    private final DepartamentoRepository repository;
    private final ValidatorAutoThrow validator;
    private final Operador operadorLogado;

    @Override
    public Departamento criar(Departamento departamento) {
        validator.validate(departamento);
        departamento.setDataCriacao(LocalDateTime.now());
        return repository.save(departamento);
    }

    @Override
    public Departamento buscarPorId(UUID id) {
        Departamento departamentoEncontrado = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("${departamento_nao_encontrado}"));

        Set<UUID> idOperadoresAssociadoAoDepartamentoEncontrado = repository.buscarIdOperadoresAssociadosAoDepartamento(departamentoEncontrado.getId());
        if (!idOperadoresAssociadoAoDepartamentoEncontrado.contains(operadorLogado.getId())) {
            throw new ForbidenException();
        }
        return departamentoEncontrado;
    }

    @Override
    public List<Departamento> buscar(FiltroDepartamento filtro, Sort sort) {
        return repository.buscarPorDescricao(filtro.nome, sort);
    }

    @Override
    public Page<Departamento> buscarPaginado(FiltroDepartamento filtro, Pageable pageable) {
        return repository.buscarPorDescricao(filtro.nome, pageable);
    }

    @Override
    public Departamento atualizar(UUID id, Departamento departamento) {
        buscarPorId(id);
        validator.validate(departamento);
        departamento.setId(id);
        Departamento departamentoAtualizado = repository.save(departamento);
        return departamentoAtualizado;
    }

    @Override
    public void excluir(UUID id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}
