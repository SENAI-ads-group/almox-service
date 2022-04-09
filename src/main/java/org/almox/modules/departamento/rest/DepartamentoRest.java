package org.almox.modules.departamento.rest;

import lombok.RequiredArgsConstructor;
import org.almox.core.rest.RestCollection;
import org.almox.modules.auditoria.FiltroStatusAuditavel;
import org.almox.modules.departamento.model.Departamento;
import org.almox.modules.departamento.model.DepartamentoDTO;
import org.almox.modules.departamento.model.DepartamentoMapper;
import org.almox.modules.departamento.model.FiltroDepartamento;
import org.almox.modules.departamento.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DepartamentoRest implements DepartamentoRestFacade {

    private final DepartamentoService departamentoService;
    private final DepartamentoMapper departamentoMapper;

    @Override
    public ResponseEntity<RestCollection<DepartamentoDTO>> buscar(
            String nome, FiltroStatusAuditavel status,
            Optional<Integer> page, Optional<Integer> size, String[] sort
    ) {
        Sort ordenacao = Sort.by(sort);
        Pageable paginacao = criarPaginacao(page, size, ordenacao);
        FiltroDepartamento filtro = FiltroDepartamento.builder().nome(nome).build();

        List<Departamento> departamentoList = page.isEmpty()
                ? departamentoService.buscar(filtro, ordenacao)
                : departamentoService.buscarPaginado(filtro, paginacao).getContent();

        RestCollection<DepartamentoDTO> departamentosDTO = new RestCollection<>(departamentoList, paginacao).mapCollection(departamentoMapper::toDTO);
        return ResponseEntity.ok(departamentosDTO);
    }

    @Override
    public ResponseEntity<DepartamentoDTO> buscarPorId(UUID id) {
        Departamento pessoa = departamentoService.buscarPorId(id);
        DepartamentoDTO dto = departamentoMapper.toDTO(pessoa);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<Void> criar(DepartamentoDTO dto) {
        Departamento departamentoCriado = departamentoService.criar(departamentoMapper.fromDTO(dto));
        URI uriCriado = getUriCriado(departamentoCriado.getId());
        return ResponseEntity.created(uriCriado).build();
    }

    @Override
    public ResponseEntity<DepartamentoDTO> atualizar(UUID id, DepartamentoDTO dto) {
        Departamento departamentoAtualizado = departamentoService.atualizar(id, departamentoMapper.fromDTO(dto));
        DepartamentoDTO departamentoAtualizadoDTO = departamentoMapper.toDTO(departamentoAtualizado);
        return ResponseEntity.ok(departamentoAtualizadoDTO);
    }

    @Override
    public ResponseEntity<Void> excluir(UUID id) {
        departamentoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
