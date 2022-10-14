package org.almox.modules.produto.dto;

import org.almox.modules.auditoria.MappingAuditavel;
import org.almox.modules.departamento.model.Departamento;
import org.almox.modules.fornecedor.model.Fornecedor;
import org.almox.modules.grupo.model.Grupo;
import org.almox.modules.produto.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.almox.modules.util.ColecaoUtil.colecaoVaziaCasoSejaNula;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    @Mappings({
            @Mapping(target = "fornecedores", expression = "java(toFornecedores(source.idFornecedores))"),
            @Mapping(target = "departamentos", expression = "java(toDepartamentos(source.idDepartamentos))"),
            @Mapping(target = "grupo", expression = "java(toGrupo(source.idGrupo))"),
            @Mapping(target = "estoque.estoqueMinimo", source = "source.estoqueMinimo"),
            @Mapping(target = "estoque.estoqueMaximo", source = "source.estoqueMaximo"),
            @Mapping(target = "estoque.controlaEstoqueMinimo", source = "source.controlaEstoqueMinimo"),
            @Mapping(target = "estoque.controlaEstoqueMaximo", source = "source.controlaEstoqueMaximo"),
    })
    Produto toProduto(CriarProdutoDTO source);

    @Mappings({
            @Mapping(target = "estoque.estoqueMinimo", source = "source.estoqueMinimo"),
            @Mapping(target = "estoque.estoqueMaximo", source = "source.estoqueMaximo"),
            @Mapping(target = "estoque.controlaEstoqueMinimo", source = "source.controlaEstoqueMinimo"),
            @Mapping(target = "estoque.controlaEstoqueMaximo", source = "source.controlaEstoqueMaximo"),
    })
    Produto toProduto(AtualizarProdutoDTO source);

    @Mappings({
            @Mapping(source = "source.estoque.estoqueMinimo", target = "estoqueMinimo"),
            @Mapping(source = "source.estoque.estoqueMaximo", target = "estoqueMaximo"),
            @Mapping(source = "source.estoque.controlaEstoqueMinimo", target = "controlaEstoqueMinimo"),
            @Mapping(source = "source.estoque.controlaEstoqueMaximo", target = "controlaEstoqueMaximo"),
    })
    @MappingAuditavel
    ProdutoDTO toDTO(Produto source);

    default Set<Fornecedor> toFornecedores(Set<UUID> uuidSet) {
        return colecaoVaziaCasoSejaNula(uuidSet)
                .stream()
                .map(idFornecedor -> Fornecedor.builder().id(idFornecedor).build())
                .collect(Collectors.toSet());
    }

    default Set<Departamento> toDepartamentos(Set<UUID> uuidSet) {
        return colecaoVaziaCasoSejaNula(uuidSet)
                .stream()
                .map(idDepartamento -> Departamento.builder().id(idDepartamento).build())
                .collect(Collectors.toSet());
    }

    default Grupo toGrupo(UUID id) {
        return Grupo.builder().id(id).build();
    }
}
