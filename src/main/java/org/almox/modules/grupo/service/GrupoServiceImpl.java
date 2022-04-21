package org.almox.modules.grupo.service;

import lombok.RequiredArgsConstructor;
import org.almox.core.exceptions.EntidadeNaoEncontradaException;
import org.almox.core.exceptions.UnauthorizedException;
import org.almox.modules.grupo.dto.FiltroGrupo;
import org.almox.modules.grupo.model.Grupo;
import org.almox.modules.grupo.repository.GrupoRepository;
import org.almox.modules.operador.dto.ContextoOperador;
import org.almox.modules.operador.model.Operador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GrupoServiceImpl implements GrupoService {

    private final ContextoOperador contextoOperador;
    private final Validator validator;
    private final GrupoRepository grupoRepository;

    @Override
    public Grupo criar(Grupo grupo) {
        validator.validate(grupo);
        grupo.setDataCriacao(LocalDateTime.now());
        return grupoRepository.save(grupo);
    }

    @Override
    public Grupo buscarPorId(UUID id) {
        Grupo grupoEncontrado = grupoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("${grupo_nao_encontrado}"));
        return grupoEncontrado;
    }

    @Override
    public Page<Grupo> buscar(FiltroGrupo filtro, Pageable paginacao) {
        validator.validate(filtro);
        return grupoRepository.buscarAtivosPorDescricao(filtro.descricao, paginacao);
    }

    @Override
    public Page<Grupo> buscarExcluidos(FiltroGrupo filtro, Pageable paginacao) {
        validator.validate(filtro);
        return grupoRepository.buscarExcluidosPorDescricao(filtro.descricao, paginacao);
    }

    @Transactional
    @Override
    public Grupo atualizar(UUID id, Grupo grupo) {
        Grupo grupoEncontrado = buscarPorId(id);
        grupo.setId(id);
        grupo.setCriadoPor(grupoEncontrado.getCriadoPor());
        grupo.setDataCriacao(grupoEncontrado.getDataCriacao());

        validator.validate(grupo);
        Grupo grupoAtualizado = grupoRepository.save(grupo);
        return grupoAtualizado;
    }

    @Override
    public void excluir(UUID id) {
        Grupo grupoASerExcluido = buscarPorId(id);
        Operador operadorLogado = contextoOperador.getOperadorLogado().orElseThrow(UnauthorizedException::new);

        setExclusaoAuditoria(grupoASerExcluido, operadorLogado);
        grupoRepository.save(grupoASerExcluido);
    }
}
