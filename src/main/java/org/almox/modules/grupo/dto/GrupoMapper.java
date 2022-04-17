package org.almox.modules.grupo.dto;

import org.almox.modules.auditoria.MappingAuditavel;
import org.almox.modules.grupo.model.Grupo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GrupoMapper {

    Grupo fromDTO(GrupoDTO dto);

    @MappingAuditavel
    GrupoDTO toDTO(Grupo source);

    List<Grupo> fromDTOList(List<GrupoDTO> dtos);

    List<GrupoDTO> toDTOList(List<Grupo> departamentos);
}
