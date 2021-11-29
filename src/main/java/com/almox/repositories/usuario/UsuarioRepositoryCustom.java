package com.almox.repositories.usuario;

import com.almox.model.dto.FiltroUsuarioDTO;
import com.almox.model.entidades.Usuario;

import java.util.List;

public interface UsuarioRepositoryCustom {

    List<Usuario> findAll(FiltroUsuarioDTO filtro);

}
