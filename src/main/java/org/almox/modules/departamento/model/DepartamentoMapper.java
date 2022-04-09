package org.almox.modules.departamento.model;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartamentoMapper {


    Departamento fromDTO(DepartamentoDTO dto);

    DepartamentoDTO toDTO(Departamento departamento);

    List<Departamento> fromDTOList(List<DepartamentoDTO> dtos);

    List<DepartamentoDTO> toDTOList(List<Departamento> departamentos);
}
