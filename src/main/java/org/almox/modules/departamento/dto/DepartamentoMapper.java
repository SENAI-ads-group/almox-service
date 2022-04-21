package org.almox.modules.departamento.dto;

import org.almox.modules.auditoria.MappingAuditavel;
import org.almox.modules.departamento.model.Departamento;
import org.almox.modules.operador.model.Operador;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.almox.modules.util.ColecaoUtil.colecaoVaziaCasoSejaNula;

@Mapper(componentModel = "spring")
public interface DepartamentoMapper {

    Departamento fromDTO(DepartamentoDTO dto);

    @Mappings({
            @Mapping(target = "operadores", expression = "java( idOperadoresToOperadores(dto.idOperadores) )")
    })
    Departamento fromDTO(CriarDepartamentoDTO dto);

    @MappingAuditavel
    DepartamentoDTO toDTO(Departamento source);

    List<Departamento> fromDTOList(List<DepartamentoDTO> dtos);

    List<DepartamentoDTO> toDTOList(List<Departamento> departamentos);

    default Set<Operador> idOperadoresToOperadores(Set<UUID> idOperadores) {
        return colecaoVaziaCasoSejaNula(idOperadores).stream()
                .map(idOperador -> Operador.builder().id(idOperador).build())
                .collect(Collectors.toSet());
    }
}
