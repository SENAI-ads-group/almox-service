package com.almox.repositories;

import com.almox.model.dto.FiltroUsuarioDTO;
import com.almox.model.entidades.Usuario;

import java.util.List;

public interface UsuarioRepositoryCustom {

    List<Usuario> findAll(FiltroUsuarioDTO filtro);

}
