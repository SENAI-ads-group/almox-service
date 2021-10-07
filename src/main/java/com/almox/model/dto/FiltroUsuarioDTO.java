package com.almox.model.dto;

import com.almox.model.enums.FiltroConsideracaoAtivos;
import com.almox.model.enums.TipoUsuario;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FiltroUsuarioDTO implements Serializable {
    private String nome;
    private String email;
    private TipoUsuario tipoUsuario;
    private FiltroConsideracaoAtivos consideracaoAtivos;
}
