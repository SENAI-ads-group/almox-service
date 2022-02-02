package com.almox.modules.departamento.service;

import com.almox.modules.departamento.model.Departamento;
import com.almox.modules.departamento.model.FiltroDepartamentoDTO;
import com.almox.modules.usuario.model.Usuario;
import com.almox.modules.common.ICrudService;

import java.util.List;

public interface IDepartamentoService extends ICrudService<Departamento, FiltroDepartamentoDTO> {
    List<Departamento> buscarAssociadosUsuario(Usuario usuarioLogado);
}
