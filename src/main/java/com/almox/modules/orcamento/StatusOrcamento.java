package com.almox.modules.orcamento;

import com.almox.modules.common.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusOrcamento implements IEnum {
    PLANEJADO("Planejado"),
    ABERTO("Aberto"),
    FECHADO("Fechado");

    @Getter
    private String descricao;

    StatusOrcamento(String descricao) {
        this.descricao = descricao;
    }

    @JsonCreator
    public static StatusOrcamento deserialize(@JsonProperty("type") String type) {
        return IEnum.fromType(values(), type);
    }

    @Override
    public String getType() {
        return name();
    }
}
