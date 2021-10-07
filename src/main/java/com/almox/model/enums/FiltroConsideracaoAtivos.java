package com.almox.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum FiltroConsideracaoAtivos implements IEnum {
    CONSIDERAR_TODOS("Considerar Todos"),
    APENAS_ATIVOS("Considerar Apenas Ativos"),
    APENAS_EXCLUIDOS("Considerar Apenas Excluídos");

    private String descricao;

    FiltroConsideracaoAtivos(String descricao) {
        this.descricao = descricao;
    }

    @JsonCreator
    public static FiltroConsideracaoAtivos deserialize(@JsonProperty("type") String type) {
        return IEnum.fromType(values(), type);
    }

    @Override
    public String getType() {
        return name();
    }
}
