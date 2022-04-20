package org.almox.modules.operador.model;

import lombok.*;
import org.almox.modules.common.EntidadePadrao;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Table(name = "FUNC_FUNCAO")
@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Funcao implements EntidadePadrao {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "FUNC_ID")
    private UUID id;

    @NotBlank(message = "${Funcao.nome.NotBlank}")
    @Column(name = "FUNC_NOME", unique = true)
    private String nome;
}
