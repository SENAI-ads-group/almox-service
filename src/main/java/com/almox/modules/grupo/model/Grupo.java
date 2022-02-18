package com.almox.modules.grupo.model;

import com.almox.modules.auditoria.Auditavel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
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
    @Size(message = "{grupo.descricao.size}",min = 4,max = 100)
    @Column(name = "grp_descricao", nullable = false)
    private String descricao;

}
