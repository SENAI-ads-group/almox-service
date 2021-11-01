package com.almox.converters;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

@Converter(autoApply = true)
public class ListaPalavraChaveConverter implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(List<String> input) {
        if (input == null) {
            return null;
        }
        return String.join(";", Sets.newHashSet(input));
    }

    @Override
    public List<String> convertToEntityAttribute(String input) {
        if (input == null) {
            return null;
        }
        return Lists.newArrayList(input.split(";"));
    }
}
