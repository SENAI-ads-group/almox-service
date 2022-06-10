package org.almox.modules.pedido.service;

import org.almox.modules.pedido.dto.FiltroPedido;
import org.almox.modules.pedido.model.ItemPedido;
import org.almox.modules.pedido.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;
import java.util.UUID;

public interface PedidoService {
    Pedido criar(Pedido pedido);

    Pedido buscarPorId(UUID id);

    Page<Pedido> buscar(FiltroPedido filtro, Pageable paginacao);

    void excluir(UUID id);

    void cancelarPedido(UUID id);

    void receberPedido(UUID id);

    Pedido alterarItens(UUID id, Set<ItemPedido> toItemRequisiao);
}
