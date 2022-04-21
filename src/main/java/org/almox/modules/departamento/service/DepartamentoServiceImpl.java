package org.almox.modules.departamento.service;

import lombok.RequiredArgsConstructor;
import org.almox.core.exceptions.EntidadeNaoEncontradaException;
import org.almox.core.exceptions.ForbiddenException;
import org.almox.core.exceptions.RegraNegocioException;
import org.almox.modules.departamento.dto.FiltroDepartamento;
import org.almox.modules.departamento.model.Departamento;
import org.almox.modules.departamento.repository.DepartamentoRepository;
import org.almox.modules.operador.dto.ContextoOperador;
import org.almox.modules.operador.model.Operador;
import org.almox.modules.operador.service.OperadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Validator;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DepartamentoServiceImpl implements DepartamentoService {

    private final Validator validator;
    private final DepartamentoRepository departamentoRepository;
    private final OperadorService operadorService;
    private final ContextoOperador contextoOperador;

    @Override
    public Departamento criar(Departamento departamento) {
        validator.validate(departamento);
        departamentoRepository.findFirstByDescricaoEquals(departamento.getDescricao()).ifPresent(departamentoComMesmaDescricao -> {
            throw new RegraNegocioException("${descricao_ja_cadastrada}");
        });
        Set<Operador> operadoresValidados = departamento.getOperadores().stream()
                .map(Operador::getId)
                .map(operadorService::buscarPorId)
                .collect(Collectors.toSet());

        departamento.setOperadores(operadoresValidados);
        return departamentoRepository.save(departamento);
    }

    @Override
    public Departamento buscarPorId(UUID id) {
        Departamento departamentoEncontrado = departamentoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("${departamento_nao_encontrado}"));
        Operador opeLogado = contextoOperador.getOperadorLogado().get();

        if (!operadorService.isAdministrador(opeLogado)) {
            Set<String> idOperadoresAssociadoAoDepartamentoEncontrado = departamentoRepository.buscarIdOperadoresAssociadosAoDepartamento(departamentoEncontrado.getId().toString());
            if (!idOperadoresAssociadoAoDepartamentoEncontrado.contains(opeLogado.getId().toString())) {
                throw new ForbiddenException("${operador_logado_nao_pertence_departamento}");
            }
        }
        return departamentoEncontrado;
    }

    @Override
    public Page<Departamento> buscar(FiltroDepartamento filtro, Pageable paginacao) {
        validator.validate(filtro);
        return departamentoRepository.buscarAtivosPorDescricao(filtro.descricao, paginacao);
    }

    @Override
    public Page<Departamento> buscarExcluidos(FiltroDepartamento filtro, Pageable paginacao) {
        validator.validate(filtro);
        return departamentoRepository.buscarExcluidosPorDescricao(filtro.descricao, paginacao);
    }

    @Transactional
    @Override
    public Departamento atualizar(UUID id, Departamento departamento) {
        Departamento departamentoEncontrado = buscarPorId(id);
        departamento.setId(id);
        atualizarEntidadeMantendoDatasAuditoria(departamento, departamentoEncontrado);

        validator.validate(departamento);
        Departamento departamentoAtualizado = departamentoRepository.save(departamento);
        return departamentoAtualizado;
    }

    @Override
    public void excluir(UUID id) {
        Departamento departamentoASerExcluido = buscarPorId(id);

        Operador opeLogado = contextoOperador.getOperadorLogado().get();
        setExclusaoAuditoria(departamentoASerExcluido, opeLogado);
        departamentoRepository.save(departamentoASerExcluido);
    }
}
