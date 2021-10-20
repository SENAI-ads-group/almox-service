package com.almox.model.entidades;

import com.almox.model.enums.TipoDeMovimento;
import com.almox.model.enums.TipoOrigemMovimento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mov_movimento")

public class Movimento extends Auditavel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mov_id")
    private Long id;

    @Future(message = "{movimento.data.futureorpresent}")
    @NotNull(message = "{movimento.data.notnull}")
    @Column(name = "mov_data", nullable = false, unique = false)
    private LocalDate data;

    @NotNull(message = "{movimento.tipoDeMovimento.notnull}")
    @Enumerated(EnumType.STRING)
    @Column(name = "mov_tipoDeMovimento", nullable = false, unique = false)
    private TipoDeMovimento tipoDeMovimento;

    @NotNull(message = "{movimento.idorigem.notnull}")
    @Column(name = "mov_id_origem", nullable = false, unique = true)
    private Long idOrigem;

    @NotNull(message = "{movimento.tipoOrigemMovimento.notnull}")
    @Enumerated(EnumType.STRING)
    @Column(name = "mov_tipoOrigemMovimento", nullable = true,  unique = true)
    private TipoOrigemMovimento tipoOrigemMovimento;

    //@OneToMany(fetch = FetchType.LAZY)
    //private Set<ItemMovimento> itensMovimento = new HashSet<>();

}
