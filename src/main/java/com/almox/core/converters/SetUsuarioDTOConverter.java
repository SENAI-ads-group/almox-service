package com.almox.core.converters;

import com.almox.modules.usuario.model.UsuarioDTO;
import com.almox.core.security.AuthManagerService;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Converter(autoApply = true)
public class SetUsuarioDTOConverter implements AttributeConverter<Set<UsuarioDTO>, String> {

    private final AuthManagerService authManagerService;

    @Autowired
    public SetUsuarioDTOConverter(AuthManagerService authManagerService) {
        this.authManagerService = authManagerService;
    }

    @Override
    public String convertToDatabaseColumn(Set<UsuarioDTO> input) {
        if (input == null) {
            return null;
        }
        return String.join(";", Sets.newHashSet(input
                .stream()
                .map(UsuarioDTO::getId)
                .collect(Collectors.toUnmodifiableList())
        ));
    }

    @Override
    public Set<UsuarioDTO> convertToEntityAttribute(String input) {
        if (input == null) {
            return null;
        }
        return Sets.newHashSet(input.split(";"))
                .stream()
                .map(authManagerService::buscarPorId)
                .collect(Collectors.toUnmodifiableSet());
    }
}
