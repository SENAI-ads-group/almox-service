package org.almox.modules.produto.model;

import lombok.Getter;

public enum UnidadeMedida {
    KG("Quilograma(s)"),
    LT("Litro(s)"),
    UN("Unidade(s)"),
    FD("Fardo(s)"),
    CX("Caixa(s)");

    @Getter
    private String descricao;

    UnidadeMedida(String descricao) {
        this.descricao = descricao;
    }

}
