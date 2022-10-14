package org.almox.modules.pedido.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.almox.core.rest.RestCollection;
import org.almox.core.rest.RestInterface;
import org.almox.modules.pedido.dto.CriarPedidoDTO;
import org.almox.modules.pedido.dto.PedidoDTO;
import org.almox.modules.pedido.model.StatusPedido;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Tag(name = "Pedidos", description = "Operações relacionadas aos recursos de pedidos de compra")
@RequestMapping(PedidoRestFacade.PATH)
public interface PedidoRestFacade extends RestInterface {
    String PATH = "/pedidos";

    @GetMapping
    @Parameters({
            @Parameter(
                    in = ParameterIn.QUERY,
                    name = "status",
                    schema = @Schema(implementation = StatusPedido.class)
            )
    })
    @PageableAsQueryParam
    ResponseEntity<RestCollection<PedidoDTO>> buscar(
            @RequestParam(required = false) StatusPedido status,
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Integer> size,
            @RequestParam(required = false, defaultValue = "id") String[] sort
    );

    @GetMapping("/{id}")
    ResponseEntity<PedidoDTO> buscarPorId(@PathVariable("id") UUID id);

    @PostMapping
    @Operation(
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(schema = @Schema(implementation = CriarPedidoDTO.class))
            )
    )
    ResponseEntity<Void> criar(@RequestBody CriarPedidoDTO dto);

    @PutMapping("/{id}/itens")
    ResponseEntity<PedidoDTO> alterarItensPedido(@PathVariable("id") UUID id, @RequestBody Set<CriarPedidoDTO.Item> dto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> excluir(@PathVariable("id") UUID id);

    @PostMapping(value = "/{id}/cancelar")
    ResponseEntity<Void> cancelarPedido(@PathVariable("id") UUID id);

    @PostMapping(value = "/{id}/receber")
    ResponseEntity<Void> receberPedido(@PathVariable("id") UUID id);
}
