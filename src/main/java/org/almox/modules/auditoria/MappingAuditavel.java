package org.almox.modules.auditoria;

import org.mapstruct.Mapping;

@Mapping(target = "auditoria.dataCriacao", source = "source.dataCriacao")
@Mapping(target = "auditoria.dataAlteracao", source = "source.dataAlteracao")
@Mapping(target = "auditoria.dataExclusao", source = "source.dataExclusao")
@Mapping(target = "auditoria.criadoPor", source = "source.criadoPor")
@Mapping(target = "auditoria.alteradoPor", source = "source.alteradoPor")
@Mapping(target = "auditoria.excluidoPor", source = "source.excluidoPor")
@Mapping(target = "auditoria.situacao", source = "source.situacao")
@Mapping(target = "auditoria.excluido", source = "source.excluido")
public @interface MappingAuditavel {
}

