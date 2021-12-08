package com.almox.model.entidades;

import com.almox.model.dto.UsuarioDTO;
import com.almox.model.enums.TipoDeMovimento;
import com.almox.model.enums.TipoOrigemMovimento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonManagedReference;

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
import java.time.LocalDateTime;
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

    @NotNull(message = "{movimento.data.notnull}")
    @Column(name = "mov_data", nullable = false)
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    @Column(name = "mov_tipoDeMovimento")
    private TipoDeMovimento tipoDeMovimento;

    @DecimalMin(value = "0.0", message = "{Movimento.custoLiquido.DecimalMin}")
    @Column(name = "mov_custo_liquido")
    private BigDecimal custoLiquido;

    @DecimalMin(value = "0.0", message = "{Movimento.custoBruto.DecimalMin}")
    @Column(name = "mov_custo_bruto")
    private BigDecimal custoBruto;

    @NotNull(message = "{movimento.idorigem.notnull}")
    @Column(name = "mov_id_origem", nullable = false)
    private Long idOrigem;

    @NotNull(message = "{movimento.tipoOrigemMovimento.notnull}")
    @Enumerated(EnumType.STRING)
    @Column(name = "mov_tipoOrigemMovimento")
    private TipoOrigemMovimento tipoOrigemMovimento;

    @JsonManagedReference
    @OneToMany(mappedBy = "movimento", fetch = FetchType.EAGER)
    private Set<ItemMovimento> itens;

    @Builder
    public Movimento(LocalDateTime dataCriacao, LocalDateTime dataAlteracao, LocalDateTime dataExclusao,
                     UsuarioDTO criadoPor, UsuarioDTO alteradoPor, UsuarioDTO excluidoPor, Long id,
                     LocalDate data, TipoDeMovimento tipoDeMovimento, BigDecimal custoLiquido, BigDecimal custoBruto,
                     Long idOrigem, TipoOrigemMovimento tipoOrigemMovimento, Set<ItemMovimento> itens) {
        super(dataCriacao, dataAlteracao, dataExclusao, criadoPor, alteradoPor, excluidoPor);
        this.id = id;
        this.data = data;
        this.tipoDeMovimento = tipoDeMovimento;
        this.custoLiquido = custoLiquido;
        this.custoBruto = custoBruto;
        this.idOrigem = idOrigem;
        this.tipoOrigemMovimento = tipoOrigemMovimento;
        this.itens = itens;
    }
}
