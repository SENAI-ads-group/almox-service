package org.almox.modules.grupo.model;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GrupoMapper {

    Grupo fromDTO(GrupoDTO dto);

    GrupoDTO toDTO(Grupo departamento);

    List<Grupo> fromDTOList(List<GrupoDTO> dtos);

    List<GrupoDTO> toDTOList(List<Grupo> departamentos);
}
