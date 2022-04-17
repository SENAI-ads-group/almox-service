package org.almox.modules.grupo.service;

import lombok.RequiredArgsConstructor;
import org.almox.core.config.validation.ValidatorAutoThrow;
import org.almox.core.exceptions.EntidadeNaoEncontradaException;
import org.almox.modules.departamento.model.Departamento;
import org.almox.modules.grupo.dto.FiltroGrupo;
import org.almox.modules.grupo.model.Grupo;
import org.almox.modules.grupo.repository.GrupoRepository;
import org.almox.modules.operador.model.Operador;
import org.almox.modules.operador.service.OperadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GrupoServiceImpl implements GrupoService {

    private final GrupoRepository repository;
    private final OperadorService operadorService;
    private final Operador operadorLogado;
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
        validator.validate(filtro);

        boolean isAdministrador = operadorService.isAdministrador(operadorLogado);
        if (isAdministrador) {
            if (isConsiderarTodos(filtro))
                return repository.buscarPorDescricao(filtro.descricao, sort);
            else if (isConsiderarApenasExcluidos(filtro))
                return repository.buscarExcluidosPorDescricao(filtro.descricao, sort);
        }
        return repository.buscarAtivosPorDescricao(filtro.descricao, sort);
    }

    @Override
    public Page<Grupo> buscarPaginado(FiltroGrupo filtro, Pageable pageable) {
        validator.validate(filtro);

        boolean isAdministrador = operadorService.isAdministrador(operadorLogado);
        if (isAdministrador) {
            if (isConsiderarTodos(filtro))
                return repository.buscarPorDescricao(filtro.descricao, pageable);
            else if (isConsiderarApenasExcluidos(filtro))
                return repository.buscarExcluidosPorDescricao(filtro.descricao, pageable);
        }
        return repository.buscarAtivosPorDescricao(filtro.descricao, pageable);
    }

    @Transactional
    @Override
    public Grupo atualizar(UUID id, Grupo grupo) {
        Grupo grupoEncontrado = buscarPorId(id);
        grupo.setId(id);
        grupo.setCriadoPor(grupoEncontrado.getCriadoPor());
        grupo.setDataCriacao(grupoEncontrado.getDataCriacao());

        validator.validate(grupo);
        Grupo grupoAtualizado = repository.save(grupo);
        return grupoAtualizado;
    }

    @Override
    public void excluir(UUID id) {
        Grupo grupoASerExcluido = buscarPorId(id);
        setExclusaoAuditoria(grupoASerExcluido, operadorLogado);
        repository.save(grupoASerExcluido);
    }
}
