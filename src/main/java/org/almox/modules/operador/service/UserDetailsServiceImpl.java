package org.almox.modules.operador.service;

import lombok.RequiredArgsConstructor;
import org.almox.modules.operador.model.Funcao;
import org.almox.modules.operador.model.Operador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {

    private final OperadorService operadorService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return operadorService.buscarPorLoginOptional(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
