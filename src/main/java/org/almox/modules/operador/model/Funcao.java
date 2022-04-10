package org.almox.modules.operador.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
    @Column(name = "func_nome")
    private String nome;

    @Override
    public boolean isNew() {
        return id == null;
    }
}
