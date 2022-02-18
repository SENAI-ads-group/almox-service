package com.almox.modules.usuario.model;

import com.almox.modules.common.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoUsuario implements IEnum {
    SOLICITANTE("Solicitante"),
    ALMOXARIFE("Almoxarife"),
    ADMINISTRADOR("Administrador");

    private String descricao;

    TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    @JsonCreator
    public static TipoUsuario deserialize(@JsonProperty("type") String type) {
        return IEnum.fromType(values(), type);
    }

    @Override
    public String getType() {
        return name();
    }
}
