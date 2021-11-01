package com.almox.services.impl;

import com.almox.exceptions.ViolacaoIntegridadeDadosException;
import com.almox.model.dto.FiltroGrupoDTO;
import com.almox.model.entidades.Grupo;
import com.almox.model.entidades.Usuario;
import com.almox.repositories.GrupoRepository;
import com.almox.services.IGrupoService;
import com.almox.util.CondicaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class GrupoService implements IGrupoService {

    private final GrupoRepository grupoRepository;

    @Autowired
    public GrupoService(GrupoRepository grupoRepository) {
        this.grupoRepository = grupoRepository;
    }

    @Override
    public Grupo buscarPorId(Long id) {
        return CondicaoUtil.verificarEntidade(grupoRepository.findById(id));
    }

    @Override
    public List<Grupo> buscarTodos(FiltroGrupoDTO filtro) {
        return grupoRepository.findAll(filtro);
    }

    @Override
    public List<Grupo> buscarTodos() {
        return grupoRepository.findAll();
    }

    @Override
    public Grupo criar(@Valid Grupo grupo) {
        return salvar(grupo);
    }

    @Override
    public Grupo atualizar(Long id, Grupo grupo) {
        buscarPorId(id);
        return salvar(grupo);
    }

    @Override
    public void excluir(long id) {
        var grupoEncontrado = buscarPorId(id);
        grupoEncontrado.setDataExclusao(LocalDateTime.now());
        var usuarioExcluidor = new Usuario();
        usuarioExcluidor.setId(1L);
        grupoEncontrado.setExcluidoPor(usuarioExcluidor);
        grupoRepository.save(grupoEncontrado);
    }

    private Grupo salvar(Grupo grupo) {
        if (!grupoRepository.findAllByDescricao(grupo.getDescricao()).isEmpty()) {
            throw new ViolacaoIntegridadeDadosException("Não foi possível cadastrar o Grupo. Descrição já existente. ");
        }
        return grupoRepository.save(grupo);
    }
    
}
