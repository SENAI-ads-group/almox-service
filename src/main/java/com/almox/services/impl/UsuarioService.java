package com.almox.services.impl;

import com.almox.model.dto.FiltroUsuarioDTO;
import com.almox.model.dto.UsuarioDTO;
import com.almox.model.entidades.Usuario;
import com.almox.repositorios.UsuarioRepository;
import com.almox.services.IUsuarioService;
import com.almox.util.CondicaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> buscarTodos(FiltroUsuarioDTO filtro) {
        if (filtro.getTipoUsuario() == null)
            return usuarioRepository.findAllByNomeContainsAndEmailContains(filtro.getNome(), filtro.getEmail());
        else
            return usuarioRepository.findAllByTipoUsuarioAndNomeContainsAndEmailContains(filtro.getTipoUsuario(), filtro.getNome(), filtro.getEmail());
    }

    public Usuario buscarPorId(Long id) {
        return CondicaoUtil.verificarEntidade(usuarioRepository.findById(id));
    }

    public Usuario criar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario editar(Long id, UsuarioDTO usuarioDTO) {
        var usuarioEncontrado = buscarPorId(id);
        atualizarDadosUsuario(usuarioEncontrado, usuarioDTO);
        return usuarioRepository.save(usuarioEncontrado);
    }

    public void excluir(Long id) {
        var usuarioEncontrado = buscarPorId(id);
        usuarioRepository.delete(usuarioEncontrado);
    }

    private void atualizarDadosUsuario(Usuario usuario, UsuarioDTO usuarioDTO) {
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
    }
}
