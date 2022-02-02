package com.almox.modules.orcamento.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orc_grp_orcamento_grupo")
public class OrcamentoGrupo extends Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orc_grp_id")
    private Long id;

    @OneToMany(mappedBy = "orcamentoPertencente", fetch = FetchType.LAZY)
    private List<ItemOrcamentoGrupo> itensPertencentes;
}
