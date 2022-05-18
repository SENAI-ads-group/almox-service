package org.almox.modules.operador.dto;

import org.almox.modules.operador.dto.OperadorDTO;
import org.almox.modules.operador.model.Funcao;
import org.almox.modules.operador.model.Operador;
import org.almox.modules.pessoa.dto.PessoaJuridicaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OperadorMapper {

    OperadorMapper INSTANCE = Mappers.getMapper(OperadorMapper.class);

    @Mappings({
            @Mapping(target = "funcoes", expression = "java(funcoesToString(operador.getFuncoes()))")
    })
    OperadorDTO toDTO(Operador operador);

    @Mappings({
            @Mapping(target = "funcoes", expression = "java(stringsToFuncao(dto.funcoes))")
    })
    Operador fromDTO(OperadorDTO dto);

    List<Operador> fromDTOList(List<OperadorDTO> dtoList);

    List<OperadorDTO> toDTOList(List<Operador> dtoList);

    default Set<String> funcoesToString(Set<Funcao> funcoes) {
        return funcoes.stream().map(Funcao::getNome).collect(Collectors.toSet());
    }

    default Set<Funcao> stringsToFuncao(Set<String> stringSet) {
        return stringSet.stream().map(str -> Funcao.builder().nome(str).build()).collect(Collectors.toSet());
    }
}
