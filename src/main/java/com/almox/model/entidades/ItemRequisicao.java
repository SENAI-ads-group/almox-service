package com.almox.model.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "itr_item_requisicao")
public class ItemRequisicao {

    @EmbeddedId
    private ItemRequisicaoID id = new ItemRequisicaoID();

    @Column(name = "itr_quantidade")
    private BigDecimal quantidade;

    public Produto getProduto() {
        return id == null ? null : id.getProduto();
    }

    public Requisicao getRequisicao() {
        return id == null ? null : id.getRequisicao();
    }

    @Getter
    @Setter
    @Embeddable
    public static class ItemRequisicaoID implements Serializable {
        @ManyToOne
        @JoinColumn(name = "req_id")
        private Requisicao requisicao;

        @ManyToOne
        @JoinColumn(name = "prod_id")
        private Produto produto;
    }
}
