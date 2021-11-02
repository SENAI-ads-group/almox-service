package com.almox.model.entidades;

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
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "itr_item_requisicao")
public class ItemRequisicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itr_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "req_id")
    private Requisicao requisicao;

    @ManyToOne
    @JoinColumn(name = "prod_id")
    private Produto produto;

    @Column(name = "itr_quantidade")
    private BigDecimal quantidade;

}
