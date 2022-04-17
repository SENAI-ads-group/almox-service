package org.almox.modules.fornecedor.rest;

import lombok.RequiredArgsConstructor;
import org.almox.core.rest.RestCollection;
import org.almox.modules.auditoria.FiltroStatusAuditoria;
import org.almox.modules.fornecedor.model.FiltroFornecedor;
import org.almox.modules.fornecedor.model.Fornecedor;
import org.almox.modules.fornecedor.model.FornecedorDTO;
import org.almox.modules.fornecedor.model.mapper.FornecedorMapper;
import org.almox.modules.fornecedor.service.FornecedorServiceImpl;
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
public class FornecedorRest implements FornecedorRestFacade {

    private final FornecedorServiceImpl fornecedorService;
    private final FornecedorMapper fornecedorMapper;

    public ResponseEntity<RestCollection<FornecedorDTO>> buscar(
            String cnpj, String nome, FiltroStatusAuditoria.Tipo statusAuditoria,
            Optional<Integer> page, Optional<Integer> size, String[] sort
    ) {
        Sort ordenacao = Sort.by(sort);
        Pageable paginacao = criarPaginacao(page, size, ordenacao);
        FiltroFornecedor filtro = FiltroFornecedor.builder().nome(nome).cnpj(cnpj).build();

        List<Fornecedor> fornecedoresList = page.isEmpty()
                ? fornecedorService.buscar(filtro, ordenacao)
                : fornecedorService.buscarPaginado(filtro, paginacao).getContent();

        RestCollection<FornecedorDTO> fornecedoresDTO = new RestCollection<>(fornecedoresList, paginacao).mapCollection(fornecedorMapper::toDTO);
        return ResponseEntity.ok(fornecedoresDTO);
    }

    public ResponseEntity<FornecedorDTO> buscarPorId(UUID id) {
        Fornecedor fornecedor = fornecedorService.buscarPorId(id);
        FornecedorDTO dto = fornecedorMapper.toDTO(fornecedor);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<Void> criar(FornecedorDTO dto) {
        Fornecedor fornecedorCriado = fornecedorService.criar(fornecedorMapper.fromDTO(dto));
        URI uriCriado = getUriCriado(fornecedorCriado.getId());
        return ResponseEntity.created(uriCriado).build();
    }

    public ResponseEntity<FornecedorDTO> atualizar(UUID id, FornecedorDTO dto) {
        Fornecedor fornecedorAtualizado = fornecedorService.atualizar(id, fornecedorMapper.fromDTO(dto));
        FornecedorDTO fornecedorAtualizadoDTO = fornecedorMapper.toDTO(fornecedorAtualizado);
        return ResponseEntity.ok(fornecedorAtualizadoDTO);
    }

    public ResponseEntity<Void> excluir(UUID id) {
        fornecedorService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
