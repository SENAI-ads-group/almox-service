package org.almox.modules.orcamento;

import org.almox.modules.departamento.model.Departamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orc_dpto_orcamento_departamento")
public class OrcamentoDepartamento extends Orcamento {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "orc_DPTO_ID")
    private UUID id;

    @OneToMany(mappedBy = "orcamentoPertencente", fetch = FetchType.LAZY)
    private List<ItemOrcamentoDepartamento> itensPertencentes;

    @ManyToOne
    private Departamento departamento;
}
