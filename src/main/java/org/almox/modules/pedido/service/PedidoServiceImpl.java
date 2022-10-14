package org.almox.modules.pedido.service;

import lombok.RequiredArgsConstructor;
import org.almox.core.exceptions.ApplicationRuntimeException;
import org.almox.core.exceptions.EntidadeNaoEncontradaException;
import org.almox.core.exceptions.RegraNegocioException;
import org.almox.core.exceptions.UnauthorizedException;
import org.almox.modules.fornecedor.service.FornecedorService;
import org.almox.modules.movimento.MovimentoService;
import org.almox.modules.operador.ContextoOperador;
import org.almox.modules.operador.model.Operador;
import org.almox.modules.pedido.dto.FiltroPedido;
import org.almox.modules.pedido.model.ItemPedido;
import org.almox.modules.pedido.model.Pedido;
import org.almox.modules.pedido.model.StatusPedido;
import org.almox.modules.pedido.repository.ItemPedidoRepository;
import org.almox.modules.pedido.repository.PedidoRepository;
import org.almox.modules.produto.service.ProdutoService;
import org.almox.modules.requisicao.model.ItemRequisicao;
import org.almox.modules.requisicao.model.Requisicao;
import org.almox.modules.requisicao.model.StatusRequisicao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PedidoServiceImpl implements PedidoService {

    private final ContextoOperador contextoOperador;
    private final Validator validator;
    private final PedidoRepository pedidoRepository;
    private final FornecedorService fornecedorService;
    private final ProdutoService produtoService;
    private final ItemPedidoRepository itemPedidoRepository;
    private final MovimentoService movimentoService;
    
    @Override
    @Transactional
    public Pedido criar(Pedido pedido) {
        Operador operadorLogado = contextoOperador.getOperadorLogado().orElseThrow(UnauthorizedException::new);

        pedido.setComprador(operadorLogado);
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatus(StatusPedido.PENDENTE_ENTREGA);

        validator.validate(pedido);
        pedido.setFornecedor(fornecedorService.buscarPorId(pedido.getFornecedor().getId()));

        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        Set<ItemPedido> itens = salvarItens(pedido.getItens(), pedido);

        pedidoSalvo.setItens(itens);
        return pedidoSalvo;
    }

    @Override
    public Pedido buscarPorId(UUID id) {
        Pedido pedidoEncontrada = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("${pedido_nao_encontrada}"));
        return pedidoEncontrada;
    }

    @Override
    public Page<Pedido> buscar(FiltroPedido filtro, Pageable paginacao) {
        Operador operadorLogado = contextoOperador.getOperadorLogado().orElseThrow(UnauthorizedException::new);
        String status = filtro.status == null ? null : filtro.status.name();
        return pedidoRepository.buscar(status, operadorLogado.getId(), paginacao);
    }

    @Override
    @Transactional
    public void excluir(UUID id) {
        buscarPorId(id);
        pedidoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void cancelarPedido(UUID id) {
        Pedido pedidoParaCancelar = buscarPorId(id);
        if (StatusPedido.statusPermissivosCancelamento().contains(pedidoParaCancelar.getStatus()))
            throw new RegraNegocioException("Não é possível cancelar um pedido com o status " + pedidoParaCancelar.getStatus().getDescricao());

        pedidoParaCancelar.setStatus(StatusPedido.CANCELADO);
        pedidoRepository.save(pedidoParaCancelar);
    }

    @Override
    @Transactional
    public void receberPedido(UUID id) {
        Operador operadorLogado = contextoOperador.getOperadorLogado().orElseThrow(UnauthorizedException::new);

        final var pedidoEncontrado = buscarPorId(id);
        if (StatusPedido.CANCELADO.equals(pedidoEncontrado.getStatus()))
            throw new RegraNegocioException("Não é possível receber um pedido que já foi cancelado");
        if (!operadorLogado.getId().equals(pedidoEncontrado.getComprador().getId()))
            throw new RegraNegocioException("Apenas o Comprador responsável pelo pedido pode aprovar seu recebimento");

        pedidoEncontrado.setStatus(StatusPedido.RECEBIDO);
        pedidoEncontrado.setDataEntrega(LocalDateTime.now());

        movimentoService.movimentarProdutosPedido(pedidoEncontrado);
    }

    @Override
    @Transactional
    public Pedido alterarItens(UUID id, Set<ItemPedido> novosItens) {
        Pedido pedidoEncontrado = buscarPorId(id);
        if (!StatusPedido.statusPermissivosAlteracao().contains(pedidoEncontrado.getStatus())) {
            throw new RegraNegocioException("${pedido_nao_permite_alteracao}");
        }
        itemPedidoRepository.deleteAllByIdInBatch(pedidoEncontrado.getItens()
                .stream()
                .map(ItemPedido::getId)
                .collect(Collectors.toSet())
        );
        Set<ItemPedido> novosItensSalvos = salvarItens(novosItens, pedidoEncontrado);
        pedidoEncontrado.setItens(novosItensSalvos);
        return pedidoRepository.save(pedidoEncontrado);
    }

    private Set<ItemPedido> salvarItens(Set<ItemPedido> itens, Pedido pedidoPertencente) {
        Set<String> erros = new HashSet<>();
        Set<ItemPedido> itensSalvos = normalizarItensPedido(itens)
                .stream()
                .map(amostraItem -> {
                    ItemPedido item = new ItemPedido();
                    item.setQuantidade(amostraItem.getQuantidade());
                    try {
                        item.setProduto(produtoService.buscarPorId(amostraItem.getProduto().getId()));
                        item.setPedido(pedidoPertencente);
                        return itemPedidoRepository.save(item);
                    } catch (EntidadeNaoEncontradaException e) {
                        erros.add("Produto não encontrado. ID: " + amostraItem.getProduto().getId());
                        return null;
                    }
                })
                .collect(Collectors.toSet());

        if (!erros.isEmpty()) {
            throw new ApplicationRuntimeException(HttpStatus.UNPROCESSABLE_ENTITY, erros.toArray(new String[0]));
        }
        return itensSalvos;
    }

    /**
     * Este método normaliza um {@link Set<ItemPedido>} removendo itensParaNormalizar com produtos repetidos e centralizando a quanntidade repetida em apenas um item
     *
     * @param itensParaNormalizar a serem normalizados
     * @return itensParaNormalizar normalizados
     */
    private Set<ItemPedido> normalizarItensPedido(Set<ItemPedido> itensParaNormalizar) {
        List<ItemPedido> itensList = new ArrayList<>(itensParaNormalizar);

        final Set<Integer> indexParaRemover = new HashSet<>();
        for (int i = 0; i < itensList.size(); i++) {
            if (indexParaRemover.contains(i)) { // se já estiver marcado para remoção significa que era um item repetido, então o ignora
                continue;
            }
            ItemPedido itemOriginal = itensList.get(i);
            for (int j = 0; j < itensList.size(); j++) {
                boolean isMesmoProduto = itensList.get(j).getProduto().getId().equals(itemOriginal.getProduto().getId());
                boolean isMesmoItem = i == j;
                if (isMesmoProduto && !isMesmoItem) {
                    ItemPedido itemRepetido = itensList.get(j);
                    itemOriginal.adicionarQuantidade(itemRepetido.getQuantidade());
                    indexParaRemover.add(j);
                }
            }
        }

        ItemPedido[] arrayItens = itensParaNormalizar.toArray(new ItemPedido[0]);
        for (int index : indexParaRemover)
            arrayItens[index] = null;

        return Arrays.stream(arrayItens)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }
}
