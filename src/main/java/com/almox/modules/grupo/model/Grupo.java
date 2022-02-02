package com.almox.modules.grupo.model;

import com.almox.modules.auditavel.Auditavel;
import com.almox.modules.produto.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="grp_grupo")
public class Grupo extends Auditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grp_id")
    private Long id;

    @NotBlank(message = "{grupo.descricao.notblank}")
    @Size(message = "{grupo.descricao.size}",min = 4,max = 20)
    @Column(name = "grp_descricao", nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "grupo", fetch = FetchType.EAGER)
    private List<Produto> produtos;
}
