package org.almox.core.exceptions.mapper;

import org.hibernate.validator.internal.metadata.descriptor.ConstraintDescriptorImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@ControllerAdvice
public class GlobalExceptionMapper {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> constraintViolationExceptionHandler(ConstraintViolationException ex, HttpServletRequest servletRequest) {
        return ResponseEntity.status(422).body(getContraintViolationErrors(ex));
    }

    public static Map<String, String> getContraintViolationErrors(ConstraintViolationException e) {
        return e.getConstraintViolations()
                .stream()
                .filter(constraintViolation -> constraintViolation.getPropertyPath().toString() != null)
                .collect(Collectors.toMap(
                        constraintViolation -> constraintViolation.getPropertyPath().toString() + " " + ((ConstraintDescriptorImpl) constraintViolation.getConstraintDescriptor()).getAnnotationDescriptor().getType().getSimpleName(),
                        constraintViolation -> constraintViolation.getInvalidValue() instanceof List
                                ? constraintViolation.getLeafBean().toString() + " - " + constraintViolation.getMessage()
                                : constraintViolation.getMessage()
                ));
    }

}
