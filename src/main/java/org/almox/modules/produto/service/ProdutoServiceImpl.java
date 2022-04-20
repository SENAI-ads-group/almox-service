package org.almox.modules.produto.service;

import lombok.RequiredArgsConstructor;
import org.almox.core.exceptions.EntidadeNaoEncontradaException;
import org.almox.modules.operador.OperadorLogado;
import org.almox.modules.operador.model.Operador;
import org.almox.modules.produto.model.FiltroProduto;
import org.almox.modules.produto.model.HistoricoEstoqueProduto;
import org.almox.modules.produto.model.Produto;
import org.almox.modules.produto.repository.HistoricoEstoqueProdutoRepository;
import org.almox.modules.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProdutoServiceImpl implements ProdutoService {

    @OperadorLogado
    private final Operador operadorLogado;
    private final Validator validator;
    private final ProdutoRepository produtoRepository;
    private final EstoqueService estoqueService;
    private final HistoricoEstoqueProdutoRepository historicoEstoqueProdutoRepository;

    @Override
    public Produto criar(Produto produto) {
        validator.validate(produto);
        produto.setEstoque(estoqueService.salvar(produto.getEstoque()));
        return produtoRepository.save(produto);
    }

    @Override
    public Produto buscarPorId(UUID id) {
        Produto produtoEncontrado = produtoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("${produto_nao_encontrado}"));
        return produtoEncontrado;
    }

    @Override
    public Page<Produto> buscar(FiltroProduto filtro, Pageable paginacao) {
        return produtoRepository.buscarAtivos(
                filtro.descricao, filtro.codigoBarras, filtro.idGrupos, filtro.idDepartamentos,
                filtro.idFornecedor, filtro.unidadeMedida, paginacao
        );
    }

    @Override
    public Page<Produto> buscarExcluidos(FiltroProduto filtro, Pageable paginacao) {
        return produtoRepository.buscarExcluidos(
                filtro.descricao, filtro.codigoBarras, filtro.idGrupos, filtro.idDepartamentos,
                filtro.idFornecedor, filtro.unidadeMedida, paginacao
        );
    }

    @Override
    public List<HistoricoEstoqueProduto> buscarHistoricoEstoque(UUID idProduto) {
        buscarPorId(idProduto);
        return historicoEstoqueProdutoRepository.buscarPorIdProduto(idProduto);
    }

    @Override
    public Produto atualizar(UUID id, Produto produto) {
        Produto produtoEncontrado = buscarPorId(id);
        validator.validate(produto);
        produto.setId(id);
        atualizarEntidadeMantendoDatasAuditoria(produto, produtoEncontrado);

        validator.validate(produto);
        produto.setEstoque(estoqueService.salvar(produto.getEstoque()));
        Produto produtoAtualizado = produtoRepository.save(produto);
        return produtoAtualizado;
    }

    @Override
    public void excluir(UUID id) {
        Produto produtoASerExcluido = buscarPorId(id);
        setExclusaoAuditoria(produtoASerExcluido, operadorLogado);
        produtoRepository.save(produtoASerExcluido);
    }
}
