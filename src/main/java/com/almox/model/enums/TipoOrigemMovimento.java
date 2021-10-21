package com.almox.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoOrigemMovimento implements IEnum{
    SOLICITACAO("Solicitação"),
    PEDIDO("Pedido");

    @Getter
    private String descricao;

    TipoOrigemMovimento(String descricao){this.descricao=descricao;}

    @JsonCreator
    public static TipoOrigemMovimento deserialize(@JsonProperty("type")String type){
        return IEnum.fromType(values(),type);
    }

    @Override
    public String getType() {return name(); }
}
