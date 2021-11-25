package com.almox.model.dto;

import com.almox.model.enums.FiltroStatusAuditavel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FiltroFabricanteDTO {
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private FiltroStatusAuditavel filtroStatusAuditavel;

}
