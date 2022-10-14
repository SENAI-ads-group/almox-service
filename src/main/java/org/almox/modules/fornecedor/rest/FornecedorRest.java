package org.almox.modules.fornecedor.rest;

import lombok.RequiredArgsConstructor;
import org.almox.core.rest.RestCollection;
import org.almox.modules.fornecedor.dto.CriarFornecedorDTO;
import org.almox.modules.fornecedor.dto.FiltroFornecedor;
import org.almox.modules.fornecedor.model.Fornecedor;
import org.almox.modules.fornecedor.dto.FornecedorDTO;
import org.almox.modules.fornecedor.dto.FornecedorMapper;
import org.almox.modules.fornecedor.service.FornecedorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FornecedorRest implements FornecedorRestFacade {

    private final FornecedorServiceImpl fornecedorService;
    private final FornecedorMapper fornecedorMapper;

    public ResponseEntity<RestCollection<FornecedorDTO>> buscar(String cnpj, String nome, Optional<Integer> page, Optional<Integer> size, String[] sort) {
        Pageable paginacao = criarPaginacao(page, size, sort);
        FiltroFornecedor filtro = criarFiltro(nome, cnpj);

        Page<FornecedorDTO> fornecedorPage = fornecedorService.buscar(filtro, paginacao).map(fornecedorMapper::toDTO);
        return ResponseEntity.ok(RestCollection.fromPage(fornecedorPage));
    }

    @Override
    public ResponseEntity<RestCollection<FornecedorDTO>> buscarExcluidos(String cnpj, String nome, Optional<Integer> page, Optional<Integer> size, String[] sort) {
        Pageable paginacao = criarPaginacao(page, size, sort);
        FiltroFornecedor filtro = criarFiltro(nome, cnpj);

        Page<FornecedorDTO> fornecedorPage = fornecedorService.buscarExcluidos(filtro, paginacao).map(fornecedorMapper::toDTO);
        return ResponseEntity.ok(RestCollection.fromPage(fornecedorPage));
    }

    public ResponseEntity<FornecedorDTO> buscarPorId(UUID id) {
        Fornecedor fornecedor = fornecedorService.buscarPorId(id);
        FornecedorDTO dto = fornecedorMapper.toDTO(fornecedor);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<Void> criar(CriarFornecedorDTO dto) {
        Fornecedor fornecedorCriado = fornecedorService.criar(fornecedorMapper.toFornecedor(dto));
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

    private FiltroFornecedor criarFiltro(String nome, String cnpj) {
        return FiltroFornecedor.builder()
                .nome(nome)
                .cnpj(cnpj)
                .build();
    }
}
