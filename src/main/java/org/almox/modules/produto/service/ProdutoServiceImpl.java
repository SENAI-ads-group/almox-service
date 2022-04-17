package org.almox.modules.produto.service;

import lombok.RequiredArgsConstructor;
import org.almox.core.config.validation.ValidatorAutoThrow;
import org.almox.core.exceptions.EntidadeNaoEncontradaException;
import org.almox.modules.produto.model.FiltroProduto;
import org.almox.modules.produto.model.HistoricoEstoqueProduto;
import org.almox.modules.produto.model.Produto;
import org.almox.modules.produto.repository.HistoricoEstoqueProdutoRepository;
import org.almox.modules.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repository;
    private final EstoqueService estoqueService;
    private final HistoricoEstoqueProdutoRepository historicoEstoqueProdutoRepository;
    private final ValidatorAutoThrow validator;

    @Override
    public Produto criar(Produto produto) {
        validator.validate(produto);
        produto.setEstoque(estoqueService.salvar(produto.getEstoque()));
        return repository.save(produto);
    }

    @Override
    public Produto buscarPorId(UUID id) {
        Produto produtoEncontrado = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("${produto_nao_encontrado}"));
        return produtoEncontrado;
    }

    @Override
    public List<Produto> buscar(FiltroProduto filtro, Sort sort) {
        return repository.buscar(
                filtro.descricao, filtro.codigoBarras, filtro.idGrupos, filtro.idDepartamentos,
                filtro.idFornecedor, filtro.unidadeMedida, sort
        );
    }

    @Override
    public Page<Produto> buscarPaginado(FiltroProduto filtro, Pageable pageable) {
        return repository.buscar(
                filtro.descricao, filtro.codigoBarras, filtro.idGrupos, filtro.idDepartamentos,
                filtro.idFornecedor, filtro.unidadeMedida, pageable
        );
    }

    @Override
    public List<HistoricoEstoqueProduto> buscarHistoricoEstoque(UUID idProduto) {
        buscarPorId(idProduto);
        return historicoEstoqueProdutoRepository.buscarPorIdProduto(idProduto);
    }

    @Override
    public Produto atualizar(UUID id, Produto produto) {
        buscarPorId(id);
        validator.validate(produto);
        produto.setId(id);
        produto.setEstoque(estoqueService.salvar(produto.getEstoque()));
        Produto produtoAtualizado = repository.save(produto);
        return produtoAtualizado;
    }

    @Override
    public void excluir(UUID id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}
