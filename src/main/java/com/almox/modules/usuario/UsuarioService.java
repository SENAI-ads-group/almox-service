package com.almox.modules.usuario;

import com.almox.core.exceptions.ApplicationRuntimeException;
import com.almox.core.security.AuthManagerService;
import com.almox.modules.crud.ICrudService;
import com.almox.modules.usuario.model.FiltroUsuarioDTO;
import com.almox.modules.usuario.model.UsuarioDTO;
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
import java.util.stream.Collectors;

import static com.almox.modules.util.BooleanUtil.isNuloOuVazio;

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
public class UsuarioService implements ICrudService<UsuarioDTO, FiltroUsuarioDTO>, AuditorAware<UsuarioDTO> {

    private static final long DURACAO_REGISTRO_NO_CACHE = 1L;

    private static final Cache<String, UsuarioDTO> USR_CACHE = CacheBuilder.newBuilder()//
            .expireAfterWrite(DURACAO_REGISTRO_NO_CACHE, TimeUnit.MINUTES)//
            .build();

    private final AuthManagerService authManagerService;

    @Autowired
    public UsuarioService(AuthManagerService authManagerService) {
        this.authManagerService = authManagerService;
    }

    @Transactional
    public UsuarioDTO getUsuarioLogado() {
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
    public Optional<UsuarioDTO> getCurrentAuditor() {
        return Optional.ofNullable(getUsuarioLogado());
    }

    private UsuarioDTO consultarOuCriarUsuario(String login) {
        if (isNuloOuVazio(login))
            return null;

        try {
            return USR_CACHE.get(login, () -> authManagerService.buscarPorLogin(login));
        } catch (ExecutionException e) {
            String mensagemErro = "Erro ao obter usuario logado";
            log.error(mensagemErro, e);
            throw new ApplicationRuntimeException(mensagemErro, e.getMessage());
        }
    }

    @Override
    public List<UsuarioDTO> buscarTodos() {
        return authManagerService.buscarTodos();
    }

    @Override
    public List<UsuarioDTO> buscarTodos(FiltroUsuarioDTO filtro) {
        return authManagerService.buscarTodos()
                .stream()
                .filter(usr -> usr.getRoles()
                        .stream()
                        .map(UsuarioDTO.RoleDTO::getRoleName)
                        .collect(Collectors.toList()).contains(filtro.getTipoUsuario().name())
                ).collect(Collectors.toList());
    }

    public UsuarioDTO buscarPorId(String id) {
        return authManagerService.buscarPorId(id);
    }
}
