package org.almox.modules.produto.dto;

import org.almox.modules.produto.model.HistoricoEstoqueProduto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HistoricoEstoqueMapper {

    @Mappings({
            @Mapping(target = "movimento", source = "itemMovimento.movimento")
    })
    HistoricoEstoqueDTO toDTO(HistoricoEstoqueProduto historicoEstoqueProduto);

    List<HistoricoEstoqueDTO> toDTOList(List<HistoricoEstoqueProduto> historicoEstoqueProdutoList);
}
