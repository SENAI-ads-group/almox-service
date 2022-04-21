package org.almox.modules.movimento.model;

import org.almox.modules.common.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoDeMovimento implements IEnum {
    ENTRADA("Entrada"),
    SAIDA("Saída");

    @Getter
    private String descricao;

    TipoDeMovimento(String descricao){this.descricao = descricao;}

    @JsonCreator
    public static TipoDeMovimento deserialize(@JsonProperty("type")String type){
        return IEnum.fromType(values(),type);
    }

    @Override
    public String getType() {return name(); }
}
