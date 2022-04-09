package org.almox.modules.grupo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FiltroGrupo implements Serializable {
    public String descricao;
}