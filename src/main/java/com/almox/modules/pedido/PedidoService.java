package com.almox.modules.pedido;

import com.almox.core.exceptions.ApplicationRuntimeException;
import com.almox.core.exceptions.EntidadeNaoEncontradaException;
import com.almox.core.exceptions.RegraNegocioException;
import com.almox.modules.movimento.MovimentoService;
import com.almox.modules.pedido.model.FiltroPedidoDTO;
import com.almox.modules.pedido.model.ItemPedido;
import com.almox.modules.pedido.model.Pedido;
import com.almox.modules.pedido.model.StatusPedido;
import com.almox.modules.pedido.repository.ItemPedidoRepository;
import com.almox.modules.pedido.repository.PedidoRepository;
import com.almox.modules.produto.ProdutoService;
import com.almox.modules.crud.CrudService;
import com.almox.modules.usuario.UsuarioService;
import com.almox.modules.util.CondicaoUtil;
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
        // Inserir valores padr??es da Requisi????o
        entidade.setComprador(usuarioService.getUsuarioLogado());
        entidade.setStatus(StatusPedido.PENDENTE_ENTREGA);

        // Salvar a requisi????o
        var entidadeSalva = salvar(entidade);

        // Validar cada item da requisi????o e salv??-los
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
     * Este m??todo normaliza um {@link Set< ItemPedido >} removendo itens com produtos repetidos e centralizando a quanntidade repetida em apenas um item
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
            if (indexParaRemover.contains(i)) { // se j?? estiver marcado para remo????o significa que era um item repetido, ent??o o ignora
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
        // Validar cada item da requisi????o e salv??-los
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
                        erros.add("Produto n??o encontrado. ID: " + amostraItem.getProduto().getId());
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
            throw new RegraNegocioException("N??o ?? poss??vel receber um pedido que j?? foi cancelado");
        if (!usuarioService.getUsuarioLogado().equals(pedidoEncontrado.getComprador()))
            throw new RegraNegocioException("Apenas o Comprador respons??vel pelo pedido pode aprovar seu recebimento");

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
            throw new RegraNegocioException("N??o ?? poss??vel cancelar um pedido que j?? foi recebido");

        pedido.setStatus(StatusPedido.CANCELADO);
        repository.save(pedido);
    }

}