package com.almox.services.impl;

import com.almox.model.dto.FiltroUsuarioDTO;
import com.almox.model.entidades.Usuario;
import com.almox.repositories.UsuarioRepository;
import com.almox.security.AuthManagerService;
import com.almox.services.IUsuarioService;
import com.almox.util.CondicaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final AuthManagerService authManagerService;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, AuthManagerService authManagerService) {
        this.usuarioRepository = usuarioRepository;
        this.authManagerService = authManagerService;
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
//        if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
//            throw new ViolacaoIntegridadeDadosException("Não foi possível criar o usuário, pois já existe um usuário com o email informado.");
//        }
        var usuarioCriadoServidorAutenticacao = authManagerService.criarUsuario(usuario);
        return usuarioRepository.save(usuarioCriadoServidorAutenticacao);
    }

    @Transactional
    public Usuario atualizar(Long id, Usuario entidade) {
        var usuarioEncontrado = buscarPorId(id);

        return usuarioRepository.save(usuarioEncontrado);
    }

    @Override
    public void excluir(long id) {

    }

}
