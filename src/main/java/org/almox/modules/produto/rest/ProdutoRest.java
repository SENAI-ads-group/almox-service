package org.almox.modules.produto.rest;

import lombok.RequiredArgsConstructor;
import org.almox.core.rest.RestCollection;
import org.almox.modules.auditoria.FiltroStatusAuditoria;
import org.almox.modules.produto.dto.*;
import org.almox.modules.produto.model.HistoricoEstoqueProduto;
import org.almox.modules.produto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProdutoRest implements ProdutoRestFacade {

    private final ProdutoService produtoService;
    private final ProdutoMapper produtoMapper;
    private final HistoricoEstoqueMapper historicoEstoqueMapper;

    @Override
    public ResponseEntity<RestCollection<ProdutoDTO>> buscar(
            String cnpj, String nome, FiltroStatusAuditoria.Tipo statusAuditoria,
            Optional<Integer> page, Optional<Integer> size, String[] sort
    ) {
        return null;
    }

    @Override
    public ResponseEntity<ProdutoDTO> buscarPorId(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<List<HistoricoEstoqueDTO>> buscarHistoricosEstoque(UUID id) {
        List<HistoricoEstoqueProduto> historicoEstoqueProduto = produtoService.buscarHistoricoEstoque(id);
        List<HistoricoEstoqueDTO> historicoEstoqueDTO = historicoEstoqueMapper.toDTOList(historicoEstoqueProduto);
        return ResponseEntity.ok(historicoEstoqueDTO);
    }

    @Override
    public ResponseEntity<Void> criar(CriarProdutoDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<ProdutoDTO> atualizar(UUID id, AtualizarProdutoDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> excluir(UUID id) {
        return null;
    }
}
