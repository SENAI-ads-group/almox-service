package org.almox.modules.operador.model;

import lombok.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Table(name = "func_funcao")
@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Funcao implements Persistable<UUID> {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "func_id")
    private UUID id;

    @NotBlank(message = "${Funcao.nome.NotBlank}")
    @Column(name = "func_nome", unique = true)
    private String nome;

    @Override
    public boolean isNew() {
        return id == null;
    }
}
