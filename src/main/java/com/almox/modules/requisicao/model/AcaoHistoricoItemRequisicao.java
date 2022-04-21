package com.almox.modules.requisicao.model;

import com.almox.modules.common.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AcaoHistoricoItemRequisicao implements IEnum {
    ENTREGAR("Entregar"),
    AJUSTE("Ajuste"),
    CANCELAR("Cancelar"),
    CRIACAO("Criação");

    @Getter
    private String descricao;

    AcaoHistoricoItemRequisicao(String descricao) { this.descricao = descricao; }

    @JsonCreator
    public static AcaoHistoricoItemRequisicao deserialize (@JsonProperty("type") String type){
        return IEnum.fromType(values(), type);
    }

    @Override
    public String getType() {
        return null;
    }
}
