package com.almox.services.impl;

import com.almox.exceptions.ApplicationRuntimeException;
import com.almox.model.dto.FiltroUsuarioDTO;
import com.almox.model.entidades.Usuario;
import com.almox.repositories.UsuarioRepository;
import com.almox.security.AuthManagerService;
import com.almox.services.ICrudService;
import com.almox.util.CondicaoUtil;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static com.almox.util.BooleanUtil.isNuloOuVazio;

/**
 * <b>Project:</b> ALMOX - <i>Gerenciador de Estoque de Almoxarifado</i> <br>
 * <b>Company:</b> SENAI ADS Group <br>
 * <br>
 * <i>Copyright 2021, <a href='https://github.com/SENAI-ads-group'>SENAI ADS Group.</a></i> <br>
 *
 * @author Patrick-Ribeiro
 * @version Revision: 03/11/2021
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class UsuarioService implements ICrudService<Usuario, FiltroUsuarioDTO>, AuditorAware<Usuario> {

    private static final long DURACAO_REGISTRO_NO_CACHE = 1L;

    private static final Cache<String, Usuario> USR_CACHE = CacheBuilder.newBuilder()//
            .expireAfterWrite(DURACAO_REGISTRO_NO_CACHE, TimeUnit.MINUTES)//
            .build();

    private final UsuarioRepository usuarioRepository;
    private final AuthManagerService authManagerService;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, AuthManagerService authManagerService) {
        this.usuarioRepository = usuarioRepository;
        this.authManagerService = authManagerService;
    }

    @Transactional
    public Usuario getUsuarioLogado() {
        try {
            var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String loginUsuarioLogado = principal instanceof UserDetails
                    ? ((UserDetails) principal).getUsername()
                    : principal.toString();
            return consultarOuCriarUsuario(loginUsuarioLogado);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.info("Nao foi possivel obter o usuario para requisicao atual", e);
            }
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Optional<Usuario> getCurrentAuditor() {
        return Optional.ofNullable(getUsuarioLogado());
    }

    private Usuario consultarOuCriarUsuario(String login) {
        if (isNuloOuVazio(login))
            return null;

        try {
            return USR_CACHE.get(login, () -> usuarioRepository.findByLogin(login).orElseGet(() -> {
                var novoUsuario = new Usuario();
                novoUsuario.setLogin(login);
                return usuarioRepository.save(novoUsuario);
            }));
        } catch (ExecutionException e) {
            String mensagemErro = "Erro ao obter usuario logado";
            log.error(mensagemErro, e);
            throw new ApplicationRuntimeException(mensagemErro, e.getMessage());
        }
    }

    @Override
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public List<Usuario> buscarTodos(FiltroUsuarioDTO filtro) {
        return usuarioRepository.findAll(filtro);
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return CondicaoUtil.verificarEntidade(usuarioRepository.findById(id));
    }
}
