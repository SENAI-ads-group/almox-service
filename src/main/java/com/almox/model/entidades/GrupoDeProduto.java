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
@Table(name ="gp_produto")
public class GrupoDeProduto extends EntidadePadrao{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gp_id")
    private Long id;

    @NotBlank(message = "Informe uma descrição para o Grupo de Produtos!")
    @Min(message = "Informe ao menos 4 caracteres!", value = 4)
    @Column(name = "gp_descricao", nullable = false)
    private String descricao;
}
