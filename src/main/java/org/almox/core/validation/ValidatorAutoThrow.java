package org.almox.core.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;
import java.util.Set;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ValidatorAutoThrow implements Validator {

    private final Validator validator;

    private <T> Set<ConstraintViolation<T>> validar(Supplier<Set<ConstraintViolation<T>>> constraintViolationsSuplier) {
        Set<ConstraintViolation<T>> constraintViolations = constraintViolationsSuplier.get();
        if (!constraintViolations.isEmpty())
            throw new ConstraintViolationException(constraintViolations);
        else
            return constraintViolations;
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validate(final T t, final Class<?>... classes) {
        return validar(() -> validator.validate(t, classes));
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validateProperty(T t, String s, Class<?>... classes) {
        return validar(() -> validator.validateProperty(t, s, classes));
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validateValue(Class<T> aClass, String s, Object o, Class<?>... classes) {
        return validar(() -> validator.validateValue(aClass, s, o, classes));
    }

    @Override
    public BeanDescriptor getConstraintsForClass(Class<?> aClass) {
        return validator.getConstraintsForClass(aClass);
    }

    @Override
    public <T> T unwrap(Class<T> aClass) {
        return validator.unwrap(aClass);
    }

    @Override
    public ExecutableValidator forExecutables() {
        return validator.forExecutables();
    }
}
