package org.almox.modules.operador.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.almox.modules.common.EntidadePadrao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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
