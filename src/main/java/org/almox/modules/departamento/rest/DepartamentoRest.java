package org.almox.modules.departamento.rest;

import lombok.RequiredArgsConstructor;
import org.almox.core.rest.RestCollection;
import org.almox.modules.auditoria.FiltroStatusAuditoria;
import org.almox.modules.departamento.dto.CriarDepartamentoDTO;
import org.almox.modules.departamento.dto.DepartamentoDTO;
import org.almox.modules.departamento.dto.DepartamentoMapper;
import org.almox.modules.departamento.dto.FiltroDepartamento;
import org.almox.modules.departamento.model.*;
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
            String descricao, FiltroStatusAuditoria.Tipo statusAuditoria,
            Optional<Integer> page, Optional<Integer> size, String[] sort
    ) {
        Sort ordenacao = Sort.by(sort);
        Pageable paginacao = criarPaginacao(page, size, ordenacao);
        FiltroDepartamento filtro = FiltroDepartamento.builder()
                .descricao(descricao)
                .statusAuditoria(statusAuditoria)
                .build();

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

    // TODO REVISAR REGRA DE NEGÓCIO
//    @GetMapping("/associados-usuario-logado")
//    public ResponseEntity<List<Departamento>> buscarAssociadosUsuarioLogado(Principal principal) {
//        return ResponseEntity.ok(service.buscarAssociadosUsuarioLogado());
//    }
//
//    @GetMapping("/relacao-produto/{idProduto}")
//    public ResponseEntity<List<Departamento>> buscarPorRelacaoProduto(@PathVariable("idProduto") Long idProduto,
//                                                                      @RequestParam(value = "relacionados", defaultValue = "true") boolean relacionados) {
//        return ResponseEntity.ok(service.buscarPorRelacaoComProduto(idProduto, relacionados));
//    }

    @Override
    public ResponseEntity<Void> criar(CriarDepartamentoDTO dto) {
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
