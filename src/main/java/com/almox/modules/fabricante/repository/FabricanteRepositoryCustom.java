package com.almox.modules.fabricante.repository;

import com.almox.modules.fabricante.model.Fabricante;
import com.almox.modules.fabricante.model.FiltroFabricanteDTO;

import java.util.List;

public interface FabricanteRepositoryCustom {
    List<Fabricante> findAll(FiltroFabricanteDTO filtro);
}
