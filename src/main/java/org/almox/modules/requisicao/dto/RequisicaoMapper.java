package org.almox.modules.requisicao.dto;

import org.almox.modules.produto.model.Produto;
import org.almox.modules.requisicao.model.ItemRequisicao;
import org.almox.modules.requisicao.model.Requisicao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Set;
import java.util.stream.Collectors;

import static org.almox.modules.util.ColecaoUtil.colecaoVaziaCasoSejaNula;

@Mapper(componentModel = "spring")
public interface RequisicaoMapper {

    @Mappings({
            @Mapping(target = "requisitante.id", source = "dto.idOperadorRequisitante"),
            @Mapping(target = "almoxarife.id", source = "dto.idOperadorAlmoxarife"),
            @Mapping(target = "departamento.id", source = "dto.idDepartamento"),
            @Mapping(target = "itens", expression = "java( toItemRequisicao(dto.itens) )")
    })
    Requisicao toRequisicao(CriarRequisicaoDTO dto);

    RequisicaoDTO toDTO(Requisicao requisicao);

    default Set<ItemRequisicao> toItemRequisicao(Set<CriarRequisicaoDTO.Item> source) {
        return colecaoVaziaCasoSejaNula(source).stream()
                .map(i -> ItemRequisicao.builder()
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
