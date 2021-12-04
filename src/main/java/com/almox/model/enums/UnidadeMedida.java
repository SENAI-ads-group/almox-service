package com.almox.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UnidadeMedida implements IEnum{
    KG("Quilograma(s)"),
    LT("Litro(s)"),
    UN("Unidade(s)"),
    FD("Fardo(s)"),
    CX("Caixa(s)");

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
