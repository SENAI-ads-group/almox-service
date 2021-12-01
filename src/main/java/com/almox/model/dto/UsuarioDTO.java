package com.almox.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private String id;
    private String username;
    private String nome;
    private String email;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataSenha;
    private Boolean ativo;
    private Boolean bloqueado;
    private String idCriador;
    private Map<String, Object> informacoesExtras;
    private Set<RoleDTO> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioDTO that = (UsuarioDTO) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Getter
    @Setter
    public static class RoleDTO {
        private String id;
        private String roleName;
        private String descricao;
    }
}
