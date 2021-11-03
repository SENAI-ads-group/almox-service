package com.almox.services;

import com.almox.model.entidades.Usuario;
import org.springframework.data.domain.AuditorAware;

public interface IUsuarioService extends AuditorAware<Usuario> {

    Usuario getUsuarioLogado();
}
