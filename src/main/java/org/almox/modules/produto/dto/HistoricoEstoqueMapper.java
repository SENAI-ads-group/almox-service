package org.almox.modules.produto.dto;

import org.almox.modules.operador.dto.OperadorDTO;
import org.almox.modules.operador.dto.OperadorMapper;
import org.almox.modules.produto.model.HistoricoEstoqueProduto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HistoricoEstoqueMapper {

    @Mappings({
            @Mapping(target = "movimento", source = "itemMovimento.movimento"),
            @Mapping(target = "produto", expression = "java(itemProdutoToProdutoDTO(historicoEstoqueProduto))"),
            @Mapping(target = "operador", expression = "java(operadorToOperadorDTO(historicoEstoqueProduto))")
    })
    HistoricoEstoqueDTO toDTO(HistoricoEstoqueProduto historicoEstoqueProduto);

    List<HistoricoEstoqueDTO> toDTOList(List<HistoricoEstoqueProduto> historicoEstoqueProdutoList);

    default ProdutoDTO itemProdutoToProdutoDTO(HistoricoEstoqueProduto historicoEstoqueProduto) {
        ProdutoMapper produtoMapper = Mappers.getMapper(ProdutoMapper.class);
        return produtoMapper.toDTO(historicoEstoqueProduto.getProduto());
    }

    default OperadorDTO operadorToOperadorDTO(HistoricoEstoqueProduto historicoEstoqueProduto) {
        OperadorMapper operadorMapper = Mappers.getMapper(OperadorMapper.class);
        return operadorMapper.toDTO(historicoEstoqueProduto.getOperador());
    }
}
