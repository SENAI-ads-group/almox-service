package com.almox.services.impl;

import com.almox.model.entidades.HistoricoEstoqueProduto;
import com.almox.model.entidades.ItemMovimento;
import com.almox.model.entidades.ItemRequisicao;
import com.almox.model.entidades.Movimento;
import com.almox.model.entidades.Requisicao;
import com.almox.model.enums.TipoDeMovimento;
import com.almox.model.enums.TipoOrigemMovimento;
import com.almox.repositories.ConfiguracaoEstoqueProdutoRepository;
import com.almox.repositories.HistoricoEstoqueProdutoRepository;
import com.almox.repositories.ItemMovimentoRepository;
import com.almox.repositories.MovimentoRepository;
import com.almox.repositories.ProdutoRepository;
import com.almox.services.IMovimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovimentoService implements IMovimentoService {

    private final MovimentoRepository movimentoRepository;
    private final HistoricoEstoqueProdutoRepository historicoEstoqueProdutoRepository;
    private final ProdutoRepository produtoRepository;
    private final ConfiguracaoEstoqueProdutoRepository configuracaoEstoqueProdutoRepository;
    private final ItemMovimentoRepository itemMovimentoRepository;

    private Map<Class<?>, TipoDeMovimento> mapaTipoMovimentoAplicadoParaCadaOrigem = Map.of(
            Requisicao.class, TipoDeMovimento.SAIDA
    );

    private Map<Class<?>, TipoOrigemMovimento> mapaTipoOrigemMovimentoAplicadoParaCadaOrigem = Map.of(
            Requisicao.class, TipoOrigemMovimento.REQUISICAO
    );

    @Autowired
    public MovimentoService(MovimentoRepository movimentoRepository, HistoricoEstoqueProdutoRepository historicoEstoqueProdutoRepository,
                            ProdutoRepository produtoRepository, ConfiguracaoEstoqueProdutoRepository configuracaoEstoqueProdutoRepository,
                            ItemMovimentoRepository itemMovimentoRepository) {
        this.movimentoRepository = movimentoRepository;
        this.historicoEstoqueProdutoRepository = historicoEstoqueProdutoRepository;
        this.produtoRepository = produtoRepository;
        this.configuracaoEstoqueProdutoRepository = configuracaoEstoqueProdutoRepository;
        this.itemMovimentoRepository = itemMovimentoRepository;
    }

    @Override
    public Movimento movimentarProdutosRequisicao(Requisicao requisicao) {
        Set<ItemMovimento> itensMovimento = requisicao.getItens().stream()
                .map(this::criarItemMovimento)
                .peek(this::gerarHistoricoEstoqueProduto)
                .collect(Collectors.toSet());
        var custoBrutoMovimento = itensMovimento.stream()
                .map(ItemMovimento::getCustoBruto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        var custoLiquidoMovimento = itensMovimento.stream()
                .map(ItemMovimento::getCustoLiquido)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Movimento movimento = new Movimento();
        movimento.setData(LocalDate.now());
        movimento.setTipoDeMovimento(mapaTipoMovimentoAplicadoParaCadaOrigem.get(requisicao.getClass()));
        movimento.setTipoOrigemMovimento(mapaTipoOrigemMovimentoAplicadoParaCadaOrigem.get(requisicao.getClass()));
        movimento.setIdOrigem(requisicao.getId());
        movimento.setItens(itensMovimento);
        movimento.setCustoBruto(custoBrutoMovimento);
        movimento.setCustoLiquido(custoLiquidoMovimento);
        return movimentoRepository.save(movimento);
    }

    private ItemMovimento criarItemMovimento(ItemRequisicao itemRequisicao) {
        var produtoRequisicao = itemRequisicao.getProduto();
        ItemMovimento itemMovimento = new ItemMovimento();
        itemMovimento.setQuantidade(itemRequisicao.getQuantidade());
        itemMovimento.setProduto(produtoRequisicao);
        itemMovimento.setCustoLiquido(produtoRequisicao.getCustoMedio()); // patrick.ribeiro: rever esse cálculo de custo
        itemMovimento.setCustoBruto(produtoRequisicao.getCustoMedio()); // patrick.ribeiro: rever esse cálculo de custo
        return itemMovimento;
    }

    private HistoricoEstoqueProduto gerarHistoricoEstoqueProduto(ItemMovimento itemMovimento) {
        var produto = itemMovimento.getProduto();
        var estoqueAnterior = produto.getConfiguracaoEstoque().getEstoqueAtual();
        var estoqueFinal = calcularEstoqueFinal(estoqueAnterior, itemMovimento);

        HistoricoEstoqueProduto historico = new HistoricoEstoqueProduto();
        historico.setDataRegistro(LocalDateTime.now());
        historico.setItemMovimento(itemMovimento);
        historico.setProduto(produto);
        historico.setEstoqueAnterior(estoqueAnterior);
        historico.setEstoqueFinal(estoqueFinal);
        var configuracaoEstoque = produto.getConfiguracaoEstoque();
        configuracaoEstoque.setEstoqueAtual(estoqueFinal);
        configuracaoEstoqueProdutoRepository.save(configuracaoEstoque);
        return historicoEstoqueProdutoRepository.save(historico);
    }

    private BigDecimal calcularEstoqueFinal(BigDecimal estoqueAnterior, ItemMovimento itemMovimento) {
        var movimento = itemMovimento.getMovimento();
        BigDecimal estoqueFinal = null;
        switch (movimento.getTipoDeMovimento()) {
            case ENTRADA:
                estoqueFinal = estoqueAnterior.add(itemMovimento.getQuantidade());
                break;
            case SAIDA:
                estoqueFinal = estoqueAnterior.subtract(itemMovimento.getQuantidade());
                break;
            default:
                break;
        }
        return estoqueFinal;
    }
}
