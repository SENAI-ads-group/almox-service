package org.almox.modules.pedido.dto;

import org.almox.modules.produto.model.Produto;
import org.almox.modules.pedido.dto.CriarPedidoDTO;
import org.almox.modules.pedido.dto.PedidoDTO;
import org.almox.modules.pedido.model.ItemPedido;
import org.almox.modules.pedido.model.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Set;
import java.util.stream.Collectors;

import static org.almox.modules.util.ColecaoUtil.colecaoVaziaCasoSejaNula;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    @Mappings({
            @Mapping(target = "fornecedor.id", source = "dto.idFornecedor"),
            @Mapping(target = "itens", expression = "java( toItemPedido(dto.itens) )")
    })
    Pedido toPedido(CriarPedidoDTO dto);

    PedidoDTO toDTO(Pedido pedido);

    default Set<ItemPedido> toItemPedido(Set<CriarPedidoDTO.Item> source) {
        return colecaoVaziaCasoSejaNula(source).stream()
                .map(i -> ItemPedido.builder()
                        .produto(Produto.builder()
                                .id(i.idProduto)
                                .build()
                        )
                        .quantidade(i.quantidade)
                        .build()
                )
                .collect(Collectors.toSet());
    }
}
