package com.almox.services.impl;

import com.almox.model.dto.FiltroUsuarioDTO;
import com.almox.model.dto.UsuarioDTO;
import com.almox.model.entidades.Usuario;
import com.almox.repositorios.UsuarioRepository;
import com.almox.services.IUsuarioService;
import com.almox.util.CondicaoUtil;
import com.almox.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> buscarTodos(FiltroUsuarioDTO filtro) {
        final String nome = StringUtil.prepararStringParaFiltro(filtro.getNome());
        final String email = StringUtil.prepararStringParaFiltro(filtro.getEmail());
        if (filtro.getTipoUsuario() == null)
            return usuarioRepository.findAllByNomeContainsAndEmailContains(nome, email);
        else
            return usuarioRepository.findAllByTipoUsuarioAndNomeContainsAndEmailContains(filtro.getTipoUsuario(), nome, email);
    }

    public Usuario buscarPorId(Long id) {
        return CondicaoUtil.verificarEntidade(usuarioRepository.findById(id));
    }

    public Usuario criar(@Valid Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario atualizar(Long id, Usuario entidade) {
        var usuarioEncontrado = buscarPorId(id);
        atualizarDadosUsuario(usuarioEncontrado, entidade);
        return usuarioRepository.save(usuarioEncontrado);
    }

    public void excluir(Long id) {
        var usuarioEncontrado = buscarPorId(id);
        usuarioRepository.delete(usuarioEncontrado);
    }

    private void atualizarDadosUsuario(Usuario usuarioDestino, Usuario usuarioOrigem) {
        usuarioDestino.setNome(usuarioOrigem.getNome());
        usuarioDestino.setEmail(usuarioOrigem.getEmail());
    }
}
