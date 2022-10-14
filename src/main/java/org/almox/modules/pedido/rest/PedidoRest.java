package org.almox.modules.pedido.rest;

import lombok.RequiredArgsConstructor;
import org.almox.core.rest.RestCollection;
import org.almox.modules.pedido.dto.CriarPedidoDTO;
import org.almox.modules.pedido.dto.FiltroPedido;
import org.almox.modules.pedido.dto.PedidoDTO;
import org.almox.modules.pedido.dto.PedidoMapper;
import org.almox.modules.pedido.model.Pedido;
import org.almox.modules.pedido.model.StatusPedido;
import org.almox.modules.pedido.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PedidoRest implements PedidoRestFacade {

    private final PedidoService pedidoService;
    private final PedidoMapper pedidoMapper;

    @Override
    public ResponseEntity<RestCollection<PedidoDTO>> buscar(
            StatusPedido status, Optional<Integer> page, Optional<Integer> size, String[] sort
    ) {
        Pageable paginacao = criarPaginacao(page, size, sort);
        FiltroPedido filtro = new FiltroPedido();
        filtro.status = status;
        Page<PedidoDTO> pedidoPage = pedidoService.buscar(filtro, paginacao).map(pedidoMapper::toDTO);
        return ResponseEntity.ok(RestCollection.fromPage(pedidoPage));
    }

    @Override
    public ResponseEntity<PedidoDTO> buscarPorId(UUID id) {
        PedidoDTO pedidoDTOEncontrado = pedidoMapper.toDTO(pedidoService.buscarPorId(id));
        return ResponseEntity.ok(pedidoDTOEncontrado);
    }

    @Override
    public ResponseEntity<Void> criar(CriarPedidoDTO dto) {
        Pedido pedidoCriado = pedidoService.criar(pedidoMapper.toPedido(dto));
        URI uriPedidoCriado = getUriCriado(pedidoCriado.getId());
        return ResponseEntity.created(uriPedidoCriado).build();
    }

    @Override
    public ResponseEntity<PedidoDTO> alterarItensPedido(UUID id, Set<CriarPedidoDTO.Item> dto) {
        Pedido PedidoComItensAlterados = pedidoService.alterarItens(id, pedidoMapper.toItemPedido(dto));
        return ResponseEntity.ok(pedidoMapper.toDTO(PedidoComItensAlterados));
    }

    @Override
    public ResponseEntity<Void> excluir(UUID id) {
        pedidoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> cancelarPedido(UUID id) {
        pedidoService.cancelarPedido(id);
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<Void> receberPedido(UUID id) {
        pedidoService.receberPedido(id);
        return ResponseEntity.accepted().build();
    }
}
