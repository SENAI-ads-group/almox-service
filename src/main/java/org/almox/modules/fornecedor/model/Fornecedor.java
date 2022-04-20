package org.almox.modules.fornecedor.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.almox.modules.auditoria.Auditavel;
import org.almox.modules.pessoa.model.PessoaJuridica;
import org.almox.modules.produto.model.Produto;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FORN_FORNECEDOR")
public class Fornecedor extends Auditavel {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "FORN_ID")
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PESS_ID")
    private PessoaJuridica pessoa;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Produto> produtosFornecidos;

}
