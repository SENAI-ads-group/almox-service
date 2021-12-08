package com.almox.services;

import com.almox.exceptions.ApplicationRuntimeException;
import com.almox.exceptions.EntidadeNaoEncontradaException;
import com.almox.exceptions.RegraNegocioException;
import com.almox.model.dto.FiltroPedidoDTO;
import com.almox.model.entidades.pedido.ItemPedido;
import com.almox.model.entidades.pedido.Pedido;
import com.almox.model.enums.StatusPedido;
import com.almox.repositories.pedido.ItemPedidoRepository;
import com.almox.repositories.pedido.PedidoRepository;
import com.almox.util.CondicaoUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

@Service
public class PedidoService extends CrudService<Pedido, FiltroPedidoDTO> {

    @Getter
    private final PedidoRepository repository;

    private final ItemPedidoRepository itemPedidoRepository;
    private final ProdutoService produtoService;
    private final UsuarioService usuarioService;
    private final MovimentoService movimentoService;

    public PedidoService(PedidoRepository repository, ItemPedidoRepository itemPedidoRepository,
                         ProdutoService produtoService, UsuarioService usuarioService, MovimentoService movimentoService) {
        this.repository = repository;
        this.itemPedidoRepository = itemPedidoRepository;
        this.produtoService = produtoService;
        this.usuarioService = usuarioService;
        this.movimentoService = movimentoService;
    }

    @Override
    protected Pedido _criar(Pedido entidade) {
        // Inserir valores padrões da Requisição
        entidade.setComprador(usuarioService.getUsuarioLogado());
        entidade.setStatus(StatusPedido.PENDENTE_ENTREGA);

        // Salvar a requisição
        var entidadeSalva = salvar(entidade);

        // Validar cada item da requisição e salvá-los
        List<String> erros = Lists.newArrayList();
        var itens = salvarItensPedido(entidade.getItens(), entidadeSalva);

        entidadeSalva.setItens(itens);
        return entidadeSalva;
    }

    @Override
    protected Pedido _atualizar(Long id, Pedido entidade) {
        buscarPorId(id);
        entidade.setId(id);
        return salvar(entidade);
    }

    private Pedido salvar(Pedido entidade) {
        return repository.save(entidade);
    }

    @Override
    protected List<Pedido> _buscarTodos() {
        return repository.findAll();
    }

    @Override
    protected List<Pedido> _buscarTodos(FiltroPedidoDTO filtro) {
        return repository.findAll(filtro);
    }

    @Override
    protected Pedido _buscarPorId(Long id) {
        return CondicaoUtil.verificarEntidade(repository.findById(id));
    }

    /**
     * Este método normaliza um {@link Set<ItemPedido>} removendo itens com produtos repetidos e centralizando a quanntidade repetida em apenas um item
     *
     * @param itens a serem normalizados
     * @return itens normalizados
     */
    private Set<ItemPedido> normalizarItensPedido(Set<ItemPedido> itens) {
        List<ItemPedido> itensList = Lists.newArrayList(itens);
        BiPredicate<ItemPedido, ItemPedido> condicaoItemComMesmoProduto = (itemOriginal, itemComparacao) -> (
                itemComparacao.getProduto().equals(itemOriginal.getProduto()) && !itemOriginal.equals(itemComparacao)
        );

        final Set<Integer> indexParaRemover = Sets.newHashSet();
        for (int i = 0; i < itensList.size(); i++) {
            if (indexParaRemover.contains(i)) { // se já estiver marcado para remoção significa que era um item repetido, então o ignora
                continue;
            }
            var itemOriginal = itensList.get(i);
            for (int j = 0; j < itensList.size(); j++) {
                if (condicaoItemComMesmoProduto.test(itemOriginal, itensList.get(j))) {
                    var itemRepetido = itensList.get(j);
                    itemOriginal.adicionarQuantidade(itemRepetido.getQuantidade());
                    indexParaRemover.add(j);
                }
            }
        }

        ItemPedido[] arrayItens = itens.toArray(new ItemPedido[0]);
        for (int index : indexParaRemover)
            arrayItens[index] = null;

        return Arrays.stream(arrayItens)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    private Set<ItemPedido> salvarItensPedido(Set<ItemPedido> itensPedido, Pedido pedido) {
        // Validar cada item da requisição e salvá-los
        List<String> erros = Lists.newArrayList();
        var itens = normalizarItensPedido(itensPedido)
                .stream()
                .map(amostraItem -> {
                    ItemPedido item = new ItemPedido();
                    item.setQuantidade(amostraItem.getQuantidade());
                    try {
                        item.setProduto(produtoService.buscarPorId(amostraItem.getProduto().getId()));
                        item.setPedido(pedido);
                    } catch (EntidadeNaoEncontradaException e) {
                        erros.add("Produto não encontrado. ID: " + amostraItem.getProduto().getId());
                        return null;
                    }
                    return itemPedidoRepository.save(item);
                })
                .collect(Collectors.toSet());

        // Verificar se houve erros ao salvar algum dos itens
        if (!erros.isEmpty()) {
            throw new ApplicationRuntimeException(HttpStatus.UNPROCESSABLE_ENTITY, erros.toArray(new String[0]));
        }
        return itens;
    }

    public void receberPedido(Long id, Pedido pedidoEntregue) {
        final var pedidoEncontrado = buscarPorId(id);
        if (StatusPedido.CANCELADO.equals(pedidoEncontrado.getStatus()))
            throw new RegraNegocioException("Não é possível receber um pedido que já foi cancelado");
        if (!usuarioService.getUsuarioLogado().equals(pedidoEncontrado.getComprador()))
            throw new RegraNegocioException("Apenas o Comprador responsável pelo pedido pode aprovar seu recebimento");

        itemPedidoRepository.deleteAll(pedidoEncontrado.getItens());

        var itens = salvarItensPedido(pedidoEntregue.getItens(), pedidoEncontrado);
        pedidoEncontrado.setItens(itens);
        pedidoEncontrado.setStatus(StatusPedido.RECEBIDO);
        pedidoEncontrado.setDataEntrega(LocalDateTime.now());
        pedidoEntregue = repository.save(pedidoEncontrado);
        movimentoService.movimentarProdutosPedido(pedidoEntregue);
    }

    public void cancelarPedido(Long id) {
        var pedido = buscarPorId(id);
        if (StatusPedido.RECEBIDO.equals(pedido.getStatus()))
            throw new RegraNegocioException("Não é possível cancelar um pedido que já foi recebido");

        pedido.setStatus(StatusPedido.CANCELADO);
        repository.save(pedido);
    }

}