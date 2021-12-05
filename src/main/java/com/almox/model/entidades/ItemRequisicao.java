package com.almox.model.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "itr_item_requisicao")
public class ItemRequisicao extends EntidadePadrao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itr_id")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "req_id")
    private Requisicao requisicao;

    @ManyToOne
    @JoinColumn(name = "prod_id")
    private Produto produto;

    @DecimalMin(value = "0.0", inclusive = false, message = "{ItemRequisicao.quantidade.DecimalMin}")
    @Column(name = "itr_quantidade")
    private BigDecimal quantidade;

    public void adicionarQuantidade(BigDecimal quantidadeAcrescentada) {
        if (quantidade != null)
            quantidade = quantidade.add(quantidadeAcrescentada);
        else
            quantidade = quantidadeAcrescentada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ItemRequisicao that = (ItemRequisicao) o;
        return Objects.equals(id, that.id) && Objects.equals(produto, that.produto) && Objects.equals(quantidade, that.quantidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, produto, quantidade);
    }
}
