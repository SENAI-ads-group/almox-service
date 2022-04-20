package org.almox.modules.produto.rest;

import lombok.RequiredArgsConstructor;
import org.almox.core.rest.RestCollection;
import org.almox.modules.produto.dto.*;
import org.almox.modules.produto.model.FiltroProduto;
import org.almox.modules.produto.model.HistoricoEstoqueProduto;
import org.almox.modules.produto.model.Produto;
import org.almox.modules.produto.model.UnidadeMedida;
import org.almox.modules.produto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProdutoRest implements ProdutoRestFacade {

    private final ProdutoService produtoService;
    private final ProdutoMapper produtoMapper;
    private final HistoricoEstoqueMapper historicoEstoqueMapper;

    @Override
    public ResponseEntity<RestCollection<ProdutoDTO>> buscar(
            String cnpj, String descricao, String codigoBarras, Set<UUID> idGrupos,
            Set<UUID> idDepartamentos, UUID idFornecedor, UnidadeMedida unidadeMedida,
            Optional<Integer> page, Optional<Integer> size, String[] sort
    ) {
        Pageable paginacao = criarPaginacao(page, size, sort);
        FiltroProduto filtro = criarFiltro(descricao, codigoBarras, idGrupos, idDepartamentos, idFornecedor, unidadeMedida);

        Page<ProdutoDTO> produtoPage = produtoService.buscar(filtro, paginacao).map(produtoMapper::toDTO);
        return ResponseEntity.ok(RestCollection.fromPage(produtoPage));
    }

    @Override
    public ResponseEntity<RestCollection<ProdutoDTO>> buscarExcluidos(String cnpj, String descricao, String codigoBarras, Set<UUID> idGrupos, Set<UUID> idDepartamentos, UUID idFornecedor, UnidadeMedida unidadeMedida, Optional<Integer> page, Optional<Integer> size, String[] sort) {
        Pageable paginacao = criarPaginacao(page, size, sort);
        FiltroProduto filtro = criarFiltro(descricao, codigoBarras, idGrupos, idDepartamentos, idFornecedor, unidadeMedida);

        Page<ProdutoDTO> produtoPage = produtoService.buscarExcluidos(filtro, paginacao).map(produtoMapper::toDTO);
        return ResponseEntity.ok(RestCollection.fromPage(produtoPage));
    }

    @Override
    public ResponseEntity<ProdutoDTO> buscarPorId(UUID id) {
        Produto produtoEncontrado = produtoService.buscarPorId(id);
        ProdutoDTO produtoDTO = produtoMapper.toDTO(produtoEncontrado);
        return ResponseEntity.ok(produtoDTO);
    }

    @Override
    public ResponseEntity<List<HistoricoEstoqueDTO>> buscarHistoricosEstoque(UUID id) {
        List<HistoricoEstoqueProduto> historicoEstoqueProduto = produtoService.buscarHistoricoEstoque(id);
        List<HistoricoEstoqueDTO> historicoEstoqueDTO = historicoEstoqueMapper.toDTOList(historicoEstoqueProduto);
        return ResponseEntity.ok(historicoEstoqueDTO);
    }

    @Override
    public ResponseEntity<Void> criar(CriarProdutoDTO dto) {
        Produto produtoCriado = produtoService.criar(produtoMapper.toProduto(dto));
        URI uriCriado = getUriCriado(produtoCriado.getId());
        return ResponseEntity.created(uriCriado).build();
    }

    @Override
    public ResponseEntity<ProdutoDTO> atualizar(UUID id, AtualizarProdutoDTO dto) {
        Produto produtoAtualizado = produtoService.atualizar(id, produtoMapper.toProduto(dto));
        ProdutoDTO produtoAtualizadoDTO = produtoMapper.toDTO(produtoAtualizado);
        return ResponseEntity.ok(produtoAtualizadoDTO);
    }

    @Override
    public ResponseEntity<Void> excluir(UUID id) {
        produtoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    private FiltroProduto criarFiltro(String descricao, String codigoBarras, Set<UUID> idGrupos, Set<UUID> idDepartamentos, UUID idFornecedor, UnidadeMedida unidadeMedida) {
        return FiltroProduto.builder()
                .descricao(descricao)
                .codigoBarras(codigoBarras)
                .idGrupos(idGrupos)
                .idDepartamentos(idDepartamentos)
                .idFornecedor(idFornecedor)
                .unidadeMedida(unidadeMedida)
                .build();
    }
}
