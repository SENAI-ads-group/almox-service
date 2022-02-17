package com.almox.modules.grupo.repository;

import com.almox.modules.grupo.model.FiltroGrupoDTO;
import com.almox.modules.grupo.model.Grupo;

import java.util.List;

public interface GrupoRepositoryCustom {

     List<Grupo> findAll(FiltroGrupoDTO filtro);
}
