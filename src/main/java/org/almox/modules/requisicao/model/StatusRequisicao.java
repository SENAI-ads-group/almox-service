package org.almox.modules.requisicao.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.almox.modules.common.IEnum;

import java.util.Set;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum StatusRequisicao {
    AGUARDANDO_ATENDIMENTO("Aguardando Atendimento"),
    EM_ATENDIMENTO("Em Atendimento"),
    AGUARDANDO_RECEBIMENTO("Aguardando Recebimento"),
    ENTREGUE("Entregue"),
    CANCELADA("Cancelada");

    @Getter
    private String descricao;

    public static Set<StatusRequisicao> statusPermissivosAlteracao() {
        return Set.of(AGUARDANDO_ATENDIMENTO, EM_ATENDIMENTO);
    }
}
