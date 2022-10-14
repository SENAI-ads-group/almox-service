package com.almox.modules.auditoria;

import com.almox.modules.common.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusAuditavel implements IEnum {
    NOVO("Novo"),
    ATIVO("Ativo"),
    EXCLUIDO("Excluído"),
    ATUALIZADO_RECENTMENTE("Recém Atualizado");

    private final String descricao;

    StatusAuditavel(String descricao) {
        this.descricao = descricao;
    }

    @JsonCreator
    public static StatusAuditavel deserialize(@JsonProperty("type") String type) {
        return IEnum.fromType(values(), type);
    }

    @Override
    public String getType() {
        return name();
    }
}
