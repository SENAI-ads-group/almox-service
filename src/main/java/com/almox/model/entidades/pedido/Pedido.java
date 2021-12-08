package com.almox.model.entidades.pedido;

import com.almox.converters.UsuarioDTOConverter;
import com.almox.model.dto.UsuarioDTO;
import com.almox.model.entidades.EntidadePadrao;
import com.almox.model.entidades.Fornecedor;
import com.almox.model.enums.StatusPedido;
import com.almox.model.enums.StatusRequisicao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ped_pedido")
public class Pedido extends EntidadePadrao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ped_id")
    private Long id;

    @Column(name = "ped_dt_requisicao", nullable = false)
    private LocalDateTime dataPedido;

    @Column(name = "ped_dt_previsao_entrega")
    private LocalDateTime dataPrevisaoEntrega;

    @Column(name = "ped_dt_entrega")
    private LocalDateTime dataEntrega;

    @Column(name = "id_usr_comprador", nullable = false)
    @Convert(converter = UsuarioDTOConverter.class)
    private UsuarioDTO comprador;

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
