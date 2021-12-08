package com.almox.services;

import com.almox.exceptions.RegraNegocioException;
import com.almox.model.dto.FiltroProdutoDTO;
import com.almox.model.entidades.HistoricoEstoqueProduto;
import com.almox.model.entidades.Produto;
import com.almox.repositories.produto.HistoricoEstoqueProdutoRepository;
import com.almox.repositories.produto.ProdutoRepository;
import com.almox.util.CondicaoUtil;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService extends CrudService<Produto, FiltroProdutoDTO> {

    @Getter
    private final ProdutoRepository repository;
    private final ConfiguracaoEstoqueService configuracaoEstoqueService;
    private final HistoricoEstoqueProdutoRepository historicoEstoqueProdutoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository repository, ConfiguracaoEstoqueService configuracaoEstoqueService, HistoricoEstoqueProdutoRepository historicoEstoqueProdutoRepository) {
        this.repository = repository;
        this.configuracaoEstoqueService = configuracaoEstoqueService;
        this.historicoEstoqueProdutoRepository = historicoEstoqueProdutoRepository;
    }

    @Override
    public Produto _buscarPorId(Long id) {
        return CondicaoUtil.verificarEntidade(repository.findById(id));
    }

    @Override
    public List<Produto> _buscarTodos() {
        return repository.findAll();
    }

    @Override
    public List<Produto> _buscarTodos(FiltroProdutoDTO filtro) {
        return repository.findAll(filtro);
    }

    @Override
    public Produto _criar(@Valid Produto entidade) {
        if (repository.findByCodigoBarras(entidade.getCodigoBarras()).isPresent())
            throw new RegraNegocioException("Código de barras já existente");
        return salvar(entidade);
    }

    @Override
    public Produto _atualizar(Long id, Produto entidade) {
        this.buscarPorId(id);
        return salvar(entidade);
    }

    private Produto salvar(Produto entidade) {
        entidade.setConfiguracaoEstoque(configuracaoEstoqueService.salvar(entidade.getConfiguracaoEstoque()));
        return repository.save(entidade);
    }

    public BigDecimal calcularCustoMedio(Produto produto) {
        return produto.getCustoMedio(); // MOCK
    }

    public List<HistoricoEstoqueProduto> buscarHistoricosEstoque(Long id) {
        return historicoEstoqueProdutoRepository.findAllByProdutoId(id)
                .stream()
                .peek(historico -> {
                    var itemMovimento = historico.getItemMovimento();
                    var movimento = itemMovimento.getMovimento();

                    itemMovimento.setTipoDeMovimento(movimento.getTipoDeMovimento());
                    itemMovimento.setTipoOrigemMovimento(movimento.getTipoOrigemMovimento());
                    itemMovimento.setIdOrigem(movimento.getIdOrigem());
                }).collect(Collectors.toList());
    }
}
