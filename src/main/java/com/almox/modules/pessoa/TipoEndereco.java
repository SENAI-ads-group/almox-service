package com.almox.modules.pessoa;

import com.almox.modules.common.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoEndereco implements IEnum {
    RESIDENCIAL("Residencial"),
    COMERCIAL("Comercial");

    private String descricao;

    TipoEndereco(String descricao) {
        this.descricao = descricao;
    }

    @JsonCreator
    public static TipoEndereco deserialize(@JsonProperty("type") String type) {
        return IEnum.fromType(values(), type);
    }

    @Override
    public String getType() {
        return name();
    }
}
