package com.almox.services.impl;

import com.almox.model.dto.FiltroFabricanteDTO;
import com.almox.model.entidades.Fabricante;
import com.almox.model.entidades.Usuario;
import com.almox.repositories.FabricanteRepository;
import com.almox.services.IFabricanteService;
import com.almox.util.CondicaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FabricanteService implements IFabricanteService {
    private final FabricanteRepository fabricanteRepository;

    @Autowired
    public FabricanteService(FabricanteRepository fabricanteRepository) {
        this.fabricanteRepository = fabricanteRepository;
    }

    @Override
    public List<Fabricante> buscarTodos(FiltroFabricanteDTO filtro) {
        return fabricanteRepository.findAll(filtro);
    }

    @Override
    public List<Fabricante> buscarTodos() {
        return fabricanteRepository.findAll();
    }

    @Override
    public Fabricante buscarPorId(Long id) {
        return CondicaoUtil.verificarEntidade(fabricanteRepository.findById(id));
    }

    @Transactional
    public Fabricante criar(@Valid Fabricante fabricante) {
        return salvar(fabricante);

    }

    private Fabricante salvar(Fabricante entidade) {
        return fabricanteRepository.save(entidade);
    }

    @Override
    @Transactional
    public Fabricante atualizar(Long id, @Valid Fabricante entidade) {
        buscarPorId(id);
        return salvar(entidade);
    }

    @Override
    public void excluir(long id) {
        var fabricanteEcontrado = buscarPorId(id);
        fabricanteEcontrado.setDataExclusao(LocalDateTime.now());
        var usuarioExcluidor = new Usuario();
        usuarioExcluidor.setId(1L);
        fabricanteEcontrado.setExcluidoPor(usuarioExcluidor);
        fabricanteRepository.save(fabricanteEcontrado);
    }


}
