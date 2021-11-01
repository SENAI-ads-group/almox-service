package com.almox.repositories;

import com.almox.model.dto.FiltroGrupoDTO;
import com.almox.model.entidades.Grupo;

import java.util.List;

public interface GrupoRepositoryCustom {

     List<Grupo> findAll(FiltroGrupoDTO filtro);
}
