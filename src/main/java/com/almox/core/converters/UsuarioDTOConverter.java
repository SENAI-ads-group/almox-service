package com.almox.core.converters;

import com.almox.modules.usuario.model.UsuarioDTO;
import com.almox.core.security.AuthManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Component
@Converter(autoApply = true)
public class UsuarioDTOConverter implements AttributeConverter<UsuarioDTO, String> {

    private final AuthManagerService authManagerService;

    @Autowired
    public UsuarioDTOConverter(AuthManagerService authManagerService) {
        this.authManagerService = authManagerService;
    }

    @Override
    public String convertToDatabaseColumn(UsuarioDTO usuarioDTO) {
        return usuarioDTO == null ? null : usuarioDTO.getId();
    }

    @Override
    public UsuarioDTO convertToEntityAttribute(String s) {
        return s == null ? null : authManagerService.buscarPorId(s);
    }
}
