package org.almox.modules.orcamento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orc_grp_orcamento_grupo")
public class OrcamentoGrupo extends Orcamento {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "orc_grp_id")
    private UUID id;

    @OneToMany(mappedBy = "orcamentoPertencente", fetch = FetchType.LAZY)
    private List<ItemOrcamentoGrupo> itensPertencentes;
}
