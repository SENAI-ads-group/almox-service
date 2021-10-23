package com.almox.services.impl;

import com.almox.exceptions.ViolacaoIntegridadeDadosException;
import com.almox.model.dto.FiltroUsuarioDTO;
import com.almox.model.entidades.Usuario;
import com.almox.repositories.UsuarioRepository;
import com.almox.services.IUsuarioService;
import com.almox.util.CondicaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> buscarTodos(FiltroUsuarioDTO filtro) {
        return usuarioRepository.findAll(filtro);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return CondicaoUtil.verificarEntidade(usuarioRepository.findById(id));
    }

    public Usuario criar(@Valid Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
            throw new ViolacaoIntegridadeDadosException("Não foi possível criar o usuário, pois já existe um usuário com o email informado.");
        }
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario atualizar(Long id, Usuario entidade) {
        var usuarioEncontrado = buscarPorId(id);
        atualizarDadosUsuario(usuarioEncontrado, entidade);
        return usuarioRepository.save(usuarioEncontrado);
    }

    @Override
    public void excluir(long id) {
        var usuarioEncontrado = buscarPorId(id);
        usuarioEncontrado.setDataExclusao(LocalDateTime.now());
        var usuarioExcluidor = new Usuario();
        usuarioExcluidor.setId(1L);
        usuarioEncontrado.setExcluidoPor(usuarioExcluidor);
        usuarioRepository.save(usuarioEncontrado);
    }

    private void atualizarDadosUsuario(Usuario usuarioDestino, Usuario usuarioOrigem) {
        usuarioDestino.setAlteradoPor(usuarioOrigem.getAlteradoPor());
        usuarioDestino.setDataAlteracao(usuarioOrigem.getDataAlteracao());
        usuarioDestino.setNome(usuarioOrigem.getNome());
        usuarioDestino.setEmail(usuarioOrigem.getEmail());
        usuarioDestino.setTipoUsuario(usuarioOrigem.getTipoUsuario());
    }
}
