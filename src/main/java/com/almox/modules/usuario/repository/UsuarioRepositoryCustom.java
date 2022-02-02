package com.almox.modules.usuario.repository;

import com.almox.modules.usuario.model.FiltroUsuarioDTO;
import com.almox.modules.usuario.model.Usuario;

import java.util.List;

public interface UsuarioRepositoryCustom {

    List<Usuario> findAll(FiltroUsuarioDTO filtro);

}
