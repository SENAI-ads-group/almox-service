package org.almox.core.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashSet;
import java.util.List;

@Converter(autoApply = true)
public class ListaPalavraChaveConverter implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(List<String> input) {
        if (input == null) {
            return null;
        }
        return String.join(";", new HashSet(input));
    }

    @Override
    public List<String> convertToEntityAttribute(String input) {
        if (input == null) {
            return null;
        }
        return List.of(input.split(";"));
    }
}
