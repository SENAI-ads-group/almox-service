package org.almox.modules.requisicao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.almox.modules.common.EntidadePadrao;
import org.almox.modules.departamento.model.Departamento;
import org.almox.modules.operador.model.Operador;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
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
@Table(name = "req_requisicao")
public class Requisicao implements EntidadePadrao {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "req_id")
    private UUID id;

    @Column(name = "req_dt_requisicao", nullable = false)
    private LocalDateTime dataRequisicao;

    @Column(name = "req_dt_entrega")
    private LocalDateTime dataEntrega;

    @ManyToOne
    @JoinColumn(name = "ope_id_requisitante")
    private Operador requisitante;

    @ManyToOne
    @JoinColumn(name = "ope_id_almoxarife")
    private Operador almoxarife;

    @NotNull(message = "{Requisicao.departamento.NotNull}")
    @ManyToOne
    @JoinColumn(name = "DPTO_ID")
    private Departamento departamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "req_status", nullable = false)
    private StatusRequisicao status;

    @NotEmpty(message = "{Requisicao.itens.NotEmpty}")
    @OneToMany(mappedBy = "requisicao", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ItemRequisicao> itens;

}
