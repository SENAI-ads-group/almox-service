package com.almox.modules.requisicao.model;

import com.almox.modules.common.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusRequisicao implements IEnum {
    PENDENTE("Pendente"),
    ABERTO("Aberto"),
    FECHADO("Fechado");

    @Getter
    private String descricao;

    StatusRequisicao(String descricao){ this.descricao=descricao; }

    @JsonCreator
    public static StatusRequisicao deserialize(@JsonProperty("type") String type){
        return IEnum.fromType(values(), type);
    }

    @Override
    public String getType() { return name(); }
}
