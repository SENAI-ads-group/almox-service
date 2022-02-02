package com.almox.modules.usuario.model;

import com.almox.modules.auditavel.FiltroStatusAuditavel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FiltroUsuarioDTO implements Serializable {
    private String nome;
    private String email;
    private TipoUsuario tipoUsuario;
    private FiltroStatusAuditavel filtroStatusAuditavel;
}
