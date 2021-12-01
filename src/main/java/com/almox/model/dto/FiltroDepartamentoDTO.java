package com.almox.model.dto;

import com.almox.model.enums.FiltroStatusAuditavel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltroDepartamentoDTO {
    private String nome;
    private FiltroStatusAuditavel filtroStatusAuditavel;
    private Long idProduto;
}
