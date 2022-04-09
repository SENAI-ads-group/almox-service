package org.almox.modules.movimento;

import org.almox.modules.movimento.model.ItemMovimento;
import org.almox.modules.movimento.repository.ItemMovimentoRepository;
import org.almox.modules.movimento.model.Movimento;
import org.almox.modules.movimento.repository.MovimentoRepository;
import org.almox.modules.movimento.model.TipoDeMovimento;
import org.almox.modules.movimento.model.TipoOrigemMovimento;
import org.almox.modules.produto.model.HistoricoEstoqueProduto;
import org.almox.modules.produto.service.ProdutoService;
import org.almox.modules.requisicao.model.ItemRequisicao;
import org.almox.modules.requisicao.model.Requisicao;
import org.almox.modules.pedido.model.ItemPedido;
import org.almox.modules.pedido.model.Pedido;
import org.almox.modules.produto.repository.ConfiguracaoEstoqueProdutoRepository;
import org.almox.modules.produto.repository.HistoricoEstoqueProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovimentoService {

    private final MovimentoRepository movimentoRepository;
    private final HistoricoEstoqueProdutoRepository historicoEstoqueProdutoRepository;
    private final ProdutoService produtoService;
    private final ConfiguracaoEstoqueProdutoRepository configuracaoEstoqueProdutoRepository;
    private final ItemMovimentoRepository itemMovimentoRepository;

    private final Map<Class<?>, TipoDeMovimento> mapaTipoMovimentoAplicadoParaCadaOrigem = Map.of(
            Requisicao.class, TipoDeMovimento.SAIDA,
            Pedido.class, TipoDeMovimento.ENTRADA
    );

    private final Map<Class<?>, TipoOrigemMovimento> mapaTipoOrigemMovimentoAplicadoParaCadaOrigem = Map.of(
            Requisicao.class, TipoOrigemMovimento.REQUISICAO,
            Pedido.class, TipoOrigemMovimento.PEDIDO
    );

    @Autowired
    public MovimentoService(MovimentoRepository movimentoRepository, HistoricoEstoqueProdutoRepository historicoEstoqueProdutoRepository,
                            ProdutoService produtoService, ConfiguracaoEstoqueProdutoRepository configuracaoEstoqueProdutoRepository,
                            ItemMovimentoRepository itemMovimentoRepository) {
        this.movimentoRepository = movimentoRepository;
        this.historicoEstoqueProdutoRepository = historicoEstoqueProdutoRepository;
        this.produtoService = produtoService;
        this.configuracaoEstoqueProdutoRepository = configuracaoEstoqueProdutoRepository;
        this.itemMovimentoRepository = itemMovimentoRepository;
    }

    @Transactional
    public Movimento movimentarProdutosRequisicao(Requisicao requisicao) {
        Movimento movimento = movimentoRepository.save(
                Movimento.builder()
                        .dataCriacao(LocalDateTime.now())
                        .criadoPor(requisicao.getAlmoxarife())
                        .data(LocalDate.now())
                        .tipoDeMovimento(mapaTipoMovimentoAplicadoParaCadaOrigem.get(requisicao.getClass()))
                        .tipoOrigemMovimento(mapaTipoOrigemMovimentoAplicadoParaCadaOrigem.get(requisicao.getClass()))
                        .idOrigem(requisicao.getId())
                        .build()
        );

        Set<ItemMovimento> itensMovimento = requisicao.getItens().stream()
                .map(itemRequisicao -> criarItemMovimento(itemRequisicao, movimento))
                .peek(this::gerarHistoricoEstoqueProduto)
                .collect(Collectors.toSet());
        var custoBrutoMovimento = itensMovimento.stream()
                .map(ItemMovimento::getCustoBruto)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        var custoLiquidoMovimento = itensMovimento.stream()
                .map(ItemMovimento::getCustoLiquido)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        movimento.setItens(itensMovimento);
        movimento.setCustoBruto(custoBrutoMovimento);
        movimento.setCustoLiquido(custoLiquidoMovimento);
        return movimentoRepository.save(movimento);
    }

    public Movimento movimentarProdutosPedido(Pedido pedido){
        Movimento movimento = movimentoRepository.save(
                Movimento.builder()
                        .dataCriacao(LocalDateTime.now())
                        .criadoPor(pedido.getComprador())
                        .data(LocalDate.now())
                        .tipoDeMovimento(mapaTipoMovimentoAplicadoParaCadaOrigem.get(pedido.getClass()))
                        .tipoOrigemMovimento(mapaTipoOrigemMovimentoAplicadoParaCadaOrigem.get(pedido.getClass()))
                        .idOrigem(pedido.getId())
                        .build()
        );

        Set<ItemMovimento> itensMovimento = pedido.getItens().stream()
                .map(itemPedido -> criarItemMovimento(itemPedido, movimento))
                .peek(this::gerarHistoricoEstoqueProduto)
                .collect(Collectors.toSet());
        var custoBrutoMovimento = itensMovimento.stream()
                .map(ItemMovimento::getCustoBruto)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        var custoLiquidoMovimento = itensMovimento.stream()
                .map(ItemMovimento::getCustoLiquido)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        movimento.setItens(itensMovimento);
        movimento.setCustoBruto(custoBrutoMovimento);
        movimento.setCustoLiquido(custoLiquidoMovimento);
        return movimentoRepository.save(movimento);
    }

    private ItemMovimento criarItemMovimento(ItemRequisicao itemRequisicao, Movimento movimento) {
        var produtoRequisicao = itemRequisicao.getProduto();
        ItemMovimento itemMovimento = new ItemMovimento();
        itemMovimento.setMovimento(movimento);
        itemMovimento.setQuantidade(itemRequisicao.getQuantidade());
        itemMovimento.setProduto(produtoRequisicao);
        itemMovimento.setCustoLiquido(produtoRequisicao.getCustoMedio()); // patrick.ribeiro: rever esse cálculo de custo
        itemMovimento.setCustoBruto(produtoRequisicao.getCustoMedio()); // patrick.ribeiro: rever esse cálculo de custo
        return itemMovimentoRepository.save(itemMovimento);
    }

    private ItemMovimento criarItemMovimento(ItemPedido itemPedido, Movimento movimento) {
        var produtoPedido = itemPedido.getProduto();
        ItemMovimento itemMovimento = new ItemMovimento();
        itemMovimento.setMovimento(movimento);
        itemMovimento.setQuantidade(itemPedido.getQuantidade());
        itemMovimento.setProduto(produtoPedido);
        itemMovimento.setCustoLiquido(produtoPedido.getCustoMedio()); // patrick.ribeiro: rever esse cálculo de custo
        itemMovimento.setCustoBruto(produtoPedido.getCustoMedio()); // patrick.ribeiro: rever esse cálculo de custo
        return itemMovimentoRepository.save(itemMovimento);
    }

    private HistoricoEstoqueProduto gerarHistoricoEstoqueProduto(ItemMovimento itemMovimento) {
        var produto = produtoService.buscarPorId(itemMovimento.getProduto().getId());
        var estoqueAnterior = produto.getConfiguracaoEstoque().getEstoqueAtual();
        var estoqueFinal = calcularEstoqueFinal(estoqueAnterior, itemMovimento);

        HistoricoEstoqueProduto historico = new HistoricoEstoqueProduto();
        historico.setDataRegistro(LocalDateTime.now());
        historico.setOperador(itemMovimento.getMovimento().getCriadoPor());
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
