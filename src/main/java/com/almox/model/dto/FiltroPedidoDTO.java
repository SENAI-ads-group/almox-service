package com.almox.model.dto;

import com.almox.model.enums.StatusPedido;
import com.almox.model.enums.StatusRequisicao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltroPedidoDTO {
    private StatusPedido status;
}
