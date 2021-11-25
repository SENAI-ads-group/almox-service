package com.almox.services;

import com.almox.model.dto.FiltroDepartamentoDTO;
import com.almox.model.entidades.Departamento;
import com.almox.model.entidades.Usuario;

import java.util.List;

public interface IDepartamentoService extends ICrudService<Departamento, FiltroDepartamentoDTO> {
    List<Departamento> buscarAssociadosUsuario(Usuario usuarioLogado);
}
