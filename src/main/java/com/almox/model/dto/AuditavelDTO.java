package com.almox.model.dto;

import com.almox.model.entidades.Auditavel;
import com.almox.model.entidades.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public abstract class AuditavelDTO<E extends Auditavel, DTO> extends EntidadeDTO<E, DTO> {
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAlteracao;
    private LocalDateTime dataExclusao;
    private Boolean excluido;
    private UsuarioAutor criadoPor;
    private UsuarioAutor alteradoPor;
    private UsuarioAutor excluidoPor;

    protected AuditavelDTO(E entidade) {
        dataCriacao = entidade.getDataCriacao();
        dataAlteracao = entidade.getDataAlteracao();
        dataExclusao = entidade.getDataExclusao();
        excluido = entidade.isExcluido();
        criadoPor = entidade.getCriadoPor() == null ? null : new UsuarioAutor(entidade.getCriadoPor());
        alteradoPor = entidade.getAlteradoPor() == null ? null : new UsuarioAutor(entidade.getAlteradoPor());
        excluidoPor = entidade.getExcluidoPor() == null ? null : new UsuarioAutor(entidade.getExcluidoPor());
    }

    @Getter
    @Setter
    public static class UsuarioAutor {
        private Long id;
        private String nome;

        public UsuarioAutor(Usuario usr) {
            id = usr.getId() == null ? null : usr.getId();
            nome = usr.getNome() == null ? null : usr.getNome();
        }
    }
}
