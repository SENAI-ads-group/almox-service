package com.almox.modules.movimento.model;

import com.almox.modules.auditavel.Auditavel;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mov_movimento")
public class Movimento extends Auditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mov_id")
    private Long id;

    @Future(message = "{movimento.data.futureorpresent}")
    @NotNull(message = "{movimento.data.notnull}")
    @Column(name = "mov_data", nullable = false)
    private LocalDate data;

    @NotNull(message = "{movimento.tipoDeMovimento.notnull}")
    @Enumerated(EnumType.STRING)
    @Column(name = "mov_tipoDeMovimento", nullable = false)
    private TipoDeMovimento tipoDeMovimento;

    @DecimalMin(value = "0.0", inclusive = false, message = "{Movimento.custoLiquido.DecimalMin}")
    @Column(name = "mov_custo_liquido", nullable = false)
    private BigDecimal custoLiquido;

    @DecimalMin(value = "0.0", inclusive = false, message = "{Movimento.custoBruto.DecimalMin}")
    @Column(name = "mov_custo_bruto", nullable = false)
    private BigDecimal custoBruto;

    @NotNull(message = "{movimento.idorigem.notnull}")
    @Column(name = "mov_id_origem", nullable = false, unique = true)
    private Long idOrigem;

    @NotNull(message = "{movimento.tipoOrigemMovimento.notnull}")
    @Enumerated(EnumType.STRING)
    @Column(name = "mov_tipoOrigemMovimento", unique = true)
    private TipoOrigemMovimento tipoOrigemMovimento;

    @OneToMany(mappedBy = "movimento", fetch = FetchType.EAGER)
    private Set<ItemMovimento> itens;

}
