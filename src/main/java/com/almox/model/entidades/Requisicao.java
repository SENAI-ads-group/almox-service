package com.almox.model.entidades;

import com.almox.model.enums.StatusRequisicao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "req_requisicao")
public class Requisicao extends EntidadePadrao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "req_id")
    private Long id;

    @Column(name = "req_dt_requisicao", nullable = false)
    private LocalDateTime dataRequisicao;

    @Column(name = "req_dt_entrega")
    private LocalDateTime dataEntrega;

    @ManyToOne
    @JoinColumn(name = "usr_id_requisitante", nullable = false)
    private Usuario requisitante;

    @ManyToOne
    @JoinColumn(name = "usr_id_almoxarife", nullable = false)
    private Usuario almoxarife;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "dpto_id")
    private Departamento departamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "req_status", nullable = false)
    private StatusRequisicao status;

    @NotEmpty(message = "{Requisicao.itens.NotEmpty}")
    @OneToMany(mappedBy = "requisicao", fetch = FetchType.EAGER)
    private List<ItemRequisicao> itens;

}
