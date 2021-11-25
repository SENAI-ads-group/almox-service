package com.almox.repositories;
import com.almox.model.dto.FiltroFabricanteDTO;
import com.almox.model.entidades.Fabricante;
import java.util.List;

public interface FabricanteRepositoryCustom {

    public List<Fabricante> findAll(FiltroFabricanteDTO filtro);
}
