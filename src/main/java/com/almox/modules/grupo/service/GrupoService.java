package com.almox.modules.grupo.service;

import com.almox.core.exceptions.ViolacaoIntegridadeDadosException;
import com.almox.modules.grupo.model.FiltroGrupoDTO;
import com.almox.modules.grupo.model.Grupo;
import com.almox.modules.grupo.repository.GrupoRepository;
import com.almox.modules.usuario.UsuarioService;
import com.almox.modules.util.CondicaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class GrupoService implements IGrupoService {

    private final GrupoRepository grupoRepository;
    private final UsuarioService usuarioService;

    @Autowired
    public GrupoService(GrupoRepository grupoRepository, UsuarioService usuarioService) {
        this.grupoRepository = grupoRepository;
        this.usuarioService = usuarioService;
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
        if (!grupoRepository.findAllByDescricao(grupo.getDescricao()).isEmpty()) {
            throw new ViolacaoIntegridadeDadosException("Não foi possível cadastrar o Grupo. Descrição já existente.");
        }
        return salvar(grupo);
    }

    @Override
    public Grupo atualizar(Long id, Grupo grupo) {
        buscarPorId(id);
        return salvar(grupo);
    }

    private Grupo salvar(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    @Override
    public void excluir(long id) {
        var entidadeEncontrada = buscarPorId(id);
        entidadeEncontrada.setDataExclusao(LocalDateTime.now());
        entidadeEncontrada.setExcluidoPor(usuarioService.getUsuarioLogado());
        grupoRepository.save(entidadeEncontrada);
    }

}