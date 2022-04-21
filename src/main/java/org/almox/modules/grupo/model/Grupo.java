package org.almox.modules.grupo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.almox.modules.auditoria.Auditavel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "GRP_GRUPO")
public class Grupo extends Auditavel {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "GRP_ID")
    private UUID id;

    @NotBlank(message = "{grupo.descricao.notblank}")
    @Size(message = "{grupo.descricao.size}", min = 4, max = 100)
    @Column(name = "GRP_DESCRICAO", nullable = false)
    private String descricao;

}
