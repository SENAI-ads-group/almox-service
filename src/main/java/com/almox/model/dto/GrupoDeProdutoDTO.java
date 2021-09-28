package com.almox.model.dto;

import com.almox.model.entidades.GrupoDeProduto;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GrupoDeProdutoDTO {

    private long id;
    private String descricao;

    public GrupoDeProdutoDTO(GrupoDeProduto grupoDP) {
        id = grupoDP.getId();
        descricao = grupoDP.getDescricao();
    }

}

