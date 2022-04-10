package org.almox.modules.pedido.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.almox.modules.common.EntidadePadrao;
import org.almox.modules.fornecedor.model.Fornecedor;
import org.almox.modules.operador.model.Operador;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ped_pedido")
public class Pedido implements EntidadePadrao {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "ped_id")
    private UUID id;

    @Column(name = "ped_dt_requisicao", nullable = false)
    private LocalDateTime dataPedido;

    @Column(name = "ped_dt_previsao_entrega")
    private LocalDateTime dataPrevisaoEntrega;

    @Column(name = "ped_dt_entrega")
    private LocalDateTime dataEntrega;

    @ManyToOne
    @JoinColumn(name = "ope_id_comprador", nullable = false)
    private Operador comprador;

    @ManyToOne
    @NotNull(message = "{Pedido.fornecedor.NotNull}")
    @JoinColumn(name = "forn_id", nullable = false)
    private Fornecedor fornecedor;

    @Enumerated(EnumType.STRING)
    @Column(name = "ped_status", nullable = false)
    private StatusPedido status;

    @NotEmpty(message = "{Pedido.itens.NotEmpty}")
    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
    private Set<ItemPedido> itens;

}
