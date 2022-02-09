package com.almox.modules.produto.service;

import com.almox.core.exceptions.ViolacaoIntegridadeDadosException;
import com.almox.modules.produto.model.FiltroProdutoDTO;
import com.almox.modules.produto.model.Produto;
import com.almox.modules.produto.repository.ProdutoRepository;
import com.almox.modules.usuario.UsuarioService;
import com.almox.modules.util.CondicaoUtil;
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
    private final UsuarioService usuarioService;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository, IConfiguracaoEstoqueService configuracaoEstoqueService, UsuarioService usuarioService) {
        this.produtoRepository = produtoRepository;
        this.configuracaoEstoqueService = configuracaoEstoqueService;
        this.usuarioService = usuarioService;
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
    public Produto criar(@Valid Produto entidade) {
        if (produtoRepository.findAllByCodigoBarras(entidade.getCodigoBarras()) != null) {
            throw new ViolacaoIntegridadeDadosException("Não foi possível cadastrar o produto. Código de barras já existente.");
        }
        return salvar(entidade);
    }

    @Override
    @Transactional
    public Produto atualizar(Long id, Produto entidade) {
        buscarPorId(id);
        return salvar(entidade);
    }

    private Produto salvar(Produto entidade) {
        entidade.setConfiguracaoEstoque(configuracaoEstoqueService.salvar(entidade.getConfiguracaoEstoque()));
        return produtoRepository.save(entidade);
    }

    @Override
    public Produto buscarPorId(Long id) {
        return CondicaoUtil.verificarEntidade(produtoRepository.findById(id));
    }

    @Override
    @Transactional
    public void excluir(long id) {
        var entidadeEncontrada = buscarPorId(id);
        entidadeEncontrada.setDataExclusao(LocalDateTime.now());
        entidadeEncontrada.setExcluidoPor(usuarioService.getUsuarioLogado());
        produtoRepository.save(entidadeEncontrada);
    }

    @Override
    public BigDecimal calcularCustoMedio(Produto produto) {
        return produto.getCustoMedio(); // MOCK
    }
}
