package org.almox.modules.pedido.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.almox.modules.common.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.almox.modules.requisicao.model.StatusRequisicao;

import java.util.Set;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum StatusPedido {
    PENDENTE_ENTREGA("Pendente de Entrega"),
    RECEBIDO("Recebido"),
    CANCELADO("Cancelado");

    @Getter
    private final String descricao;

    public static Set<StatusPedido> statusPermissivosCancelamento() {
        return Set.of(PENDENTE_ENTREGA);
    }

    public static Set<StatusPedido> statusPermissivosAlteracao() {
        return Set.of(PENDENTE_ENTREGA);
    }
}
