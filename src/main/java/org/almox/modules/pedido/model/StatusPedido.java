package org.almox.modules.pedido.model;

import org.almox.modules.common.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusPedido implements IEnum {
    PENDENTE_ENTREGA("Pendente de Entrega"),
    RECEBIDO("Recebido"),
    CANCELADO("Cancelado");

    @Getter
    private final String descricao;

    StatusPedido(String descricao){ this.descricao=descricao; }

    @JsonCreator
    public static StatusPedido deserialize(@JsonProperty("type") String type){
        return IEnum.fromType(values(), type);
    }

    @Override
    public String getType() { return name(); }
}
