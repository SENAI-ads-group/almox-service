package com.almox.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoTelefone implements IEnum {
    FIXO ("Fixo"),
    MOVEL("Móvel"),
    COMERCIAL("Comercial");

    private String descricao;

     TipoTelefone (String descricao){
        this.descricao = descricao;
    }

    @Override
    public String getType() {
        return name();
    }

    @JsonCreator
    public static TipoTelefone deserialize(@JsonProperty("type") String type) {
        return IEnum.fromType(values(), type);
    }
}
