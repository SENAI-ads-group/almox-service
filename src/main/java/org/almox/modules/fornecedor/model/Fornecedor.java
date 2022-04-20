package org.almox.modules.fornecedor.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.almox.modules.auditoria.Auditavel;
import org.almox.modules.pessoa.model.PessoaJuridica;
import org.almox.modules.produto.model.Produto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
