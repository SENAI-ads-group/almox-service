package com.almox.model.dto;

import com.almox.model.entidades.Usuario;
import com.almox.model.enums.TipoUsuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO extends AuditavelDTO<Usuario, UsuarioDTO> {
    public static final UsuarioDTO INSTANCIA = new UsuarioDTO();

    private Long id;
    private String nome;
    private String email;
    private TipoUsuario tipoUsuario;
    @JsonIgnore
    private String senha;

    public UsuarioDTO(Usuario usr) {
        super(usr);
        id = usr.getId();
        nome = usr.getNome();
        email = usr.getEmail();
        tipoUsuario = usr.getTipoUsuario();
        senha = usr.getSenha();
    }

    @Override
    public List<UsuarioDTO> entidadeListParaDTOList(Collection<Usuario> usuarioList) {
        return usuarioList.stream()
                .map(UsuarioDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO entidadeParaDTO(Usuario entidade) {
        return new UsuarioDTO(entidade);
    }

    @Override
    public Usuario dtoParaEntidade(UsuarioDTO usuarioDTO) {
        return new Usuario(usuarioDTO.getId(), usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getTipoUsuario(), usuarioDTO.getSenha());
    }
}
