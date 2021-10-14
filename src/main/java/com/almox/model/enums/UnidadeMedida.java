package com.almox.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public enum UnidadeMedida implements IEnum{
    KG("Quilograma"),
    LT("Litro"),
    UN("Unidade"),
    FD("Fardo"),
    CX("Caixa");

    @Getter
    private String descricao;

    UnidadeMedida(String descricao) {this.descricao = descricao;}

    @JsonCreator
    public static UnidadeMedida deserialize(@JsonProperty("type")String type){
        return IEnum.fromType(values(),type);
    }

    @Override
    public String getType(){return name();}
}
