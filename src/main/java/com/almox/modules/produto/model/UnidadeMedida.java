package com.almox.modules.produto.model;

import com.almox.modules.common.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UnidadeMedida implements IEnum {
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
