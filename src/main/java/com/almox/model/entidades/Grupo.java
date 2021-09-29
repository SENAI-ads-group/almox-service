package com.almox.model.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name ="grp_grupo")
public class Grupo extends Auditavel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grp_id")
    private Long id;

    @NotBlank(message = "{grupo.descricao.notblank}")
    @Min(message = "grupo.descricao.min", value = 4)
    @Column(name = "grp_descricao", nullable = false)
    private String descricao;
}
