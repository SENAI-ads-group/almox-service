package com.almox.services.impl;

import com.almox.exceptions.ViolacaoIntegridadeDadosException;
import com.almox.model.dto.FiltroProdutoDTO;
import com.almox.model.entidades.ConfiguracaoEstoqueProduto;
import com.almox.model.entidades.Produto;
import com.almox.model.entidades.Usuario;
import com.almox.repositorios.ProdutoRepository;
import com.almox.services.IConfiguracaoEstoqueService;
import com.almox.services.IProdutoService;
import com.almox.util.CondicaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProdutoService implements IProdutoService {

    private final ProdutoRepository produtoRepository;
    private final IConfiguracaoEstoqueService configuracaoEstoqueService;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository, IConfiguracaoEstoqueService configuracaoEstoqueService) {
        this.produtoRepository = produtoRepository;
        this.configuracaoEstoqueService = configuracaoEstoqueService;
    }

    @Override
    public List<Produto> buscarTodos() {
        return produtoRepository.findAll();
    }

    @Override
    public List<Produto> buscarTodos(FiltroProdutoDTO filtro) {
        return produtoRepository.findAll(filtro);
    }

    @Override
    public Produto criar(@Valid Produto produto) {
        if (produtoRepository.findAllByCodigoBarras(produto.getCodigoBarras()) != null) {
            throw new ViolacaoIntegridadeDadosException("Não foi possível cadastrar o produto. Código de barras já existente.");
        }
        ConfiguracaoEstoqueProduto configuracaoEstoque = configuracaoEstoqueService.salvar(produto.getConfiguracaoEstoque());
        produto.setConfiguracaoEstoque(configuracaoEstoque);
        return produtoRepository.save(produto);
    }

    @Override
    @Transactional
    public Produto atualizar(Long id, Produto entidade) {
        var produtoEncontrado = buscarPorId(id);
        atualizarDados(produtoEncontrado, entidade);
        return produtoRepository.save(produtoEncontrado);
    }

    @Override
    public Produto buscarPorId(Long id) {
        return CondicaoUtil.verificarEntidade(produtoRepository.findById(id));
    }

    @Override
    public void excluir(long id) {
        var produtoEncontrado = buscarPorId(id);
        produtoEncontrado.setDataExclusao(LocalDateTime.now());
        var usuarioExcluidor = new Usuario();
        usuarioExcluidor.setId(1L);
        produtoEncontrado.setExcluidoPor(usuarioExcluidor);
        produtoRepository.save(produtoEncontrado);
    }

    private void atualizarDados(Produto produtoDestino, Produto produtoOrigem) {
        produtoDestino.setCodigoBarras(produtoOrigem.getCodigoBarras());
        produtoDestino.setDepartamentos(produtoOrigem.getDepartamentos());
        produtoDestino.setDescricao(produtoOrigem.getDescricao());
        produtoDestino.setDetalhe(produtoOrigem.getDetalhe());
        produtoDestino.setCustoMedio(calcularCustoMedio(produtoOrigem));
        produtoDestino.setGrupo(produtoOrigem.getGrupo());
        produtoDestino.setPossuiLoteValidade(produtoOrigem.getPossuiLoteValidade());
        produtoDestino.setUnidadeMedida(produtoOrigem.getUnidadeMedida());
        produtoDestino.setFabricante(produtoOrigem.getFabricante());
        var configuracaoEstoque = configuracaoEstoqueService.salvar(produtoOrigem.getConfiguracaoEstoque());
        produtoDestino.setConfiguracaoEstoque(configuracaoEstoque);
    }

    private BigDecimal calcularCustoMedio(Produto produto) {
        return produto.getCustoMedio();
    }
}
