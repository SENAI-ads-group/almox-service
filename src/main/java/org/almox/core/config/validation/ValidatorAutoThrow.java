package org.almox.core.config.validation;

import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;
import java.util.Set;
import java.util.function.Supplier;

public class ValidatorAutoThrow extends LocalValidatorFactoryBean {

    private <T> Set<ConstraintViolation<T>> validar(Supplier<Set<ConstraintViolation<T>>> constraintViolationsSuplier) {
        Set<ConstraintViolation<T>> constraintViolations = constraintViolationsSuplier.get();
        if (!constraintViolations.isEmpty())
            throw new ConstraintViolationException(constraintViolations);
        else
            return constraintViolations;
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validate(final T t, final Class<?>... classes) {
        return validar(() -> super.validate(t, classes));
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validateProperty(T t, String s, Class<?>... classes) {
        return validar(() -> super.validateProperty(t, s, classes));
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validateValue(Class<T> aClass, String s, Object o, Class<?>... classes) {
        return validar(() -> super.validateValue(aClass, s, o, classes));
    }

    @Override
    public BeanDescriptor getConstraintsForClass(Class<?> aClass) {
        return super.getConstraintsForClass(aClass);
    }

    @Override
    public <T> T unwrap(Class<T> aClass) {
        return super.unwrap(aClass);
    }

    @Override
    public ExecutableValidator forExecutables() {
        return super.forExecutables();
    }
}
