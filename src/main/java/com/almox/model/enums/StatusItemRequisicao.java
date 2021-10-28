package com.almox.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public enum StatusItemRequisicao implements IEnum{
    PENDENTE("Pendente"),
    ENTREGUE("Entregue"),
    CANCELADO("Cancelado");

    @Getter
    private String descricao;

    StatusItemRequisicao(String descricao){this.descricao = descricao;}

    @JsonCreator
    public static StatusItemRequisicao deserialize(@JsonProperty("type")String type){
    return IEnum.fromType(values(), type);
    }

    @Override
    public String getType() {
        return name();
    }
}
