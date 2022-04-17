package org.almox.core.exceptions.mapper;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.almox.core.exceptions.RestException;
import org.almox.core.rest.ErroPadraoDTO;
import org.hibernate.validator.internal.metadata.descriptor.ConstraintDescriptorImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static org.almox.modules.util.ColecaoUtil.colecaoVaziaCasoSejaNula;


@ControllerAdvice
public class GlobalExceptionMapper {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> constraintViolationExceptionHandler(ConstraintViolationException ex, HttpServletRequest servletRequest) {
        return ResponseEntity.status(422).body(getContraintViolationErrors(ex));
    }

    @ExceptionHandler(RestException.class)
    public ResponseEntity<ErroPadraoDTO> restExceptionHandler(RestException ex, HttpServletRequest servletRequest) {
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getErroDTO());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErroPadraoDTO> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException ex, HttpServletRequest servletRequest) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String mensagem = String.format("Valor '%s' não aceito para o parâmetro '%s'", ex.getValue().toString(), ex.getName());
        return ResponseEntity
                .status(status)
                .body(ErroPadraoDTO.builder()
                        .status(status.value())
                        .date(LocalDateTime.now().toString())
                        .exception(ex.getClass().getSimpleName())
                        .error(status.getReasonPhrase())
                        .messages(Set.of(mensagem))
                        .build()
                );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroPadraoDTO> httpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest servletRequest) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        InvalidFormatException invalidFormatException = (InvalidFormatException) ex.getCause();
        String mensagem = String.format("Valor '%s' não aceito para o parâmetro do tipo '%s'", invalidFormatException.getValue(), invalidFormatException.getTargetType().getSimpleName());

        Set<String> paths = colecaoVaziaCasoSejaNula(invalidFormatException.getPath()).stream()
                .map(JsonMappingException.Reference::getFieldName)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        return ResponseEntity
                .status(status)
                .body(ErroPadraoDTO.builder()
                        .status(status.value())
                        .date(LocalDateTime.now().toString())
                        .exception(ex.getClass().getSimpleName())
                        .error(status.getReasonPhrase())
                        .messages(Set.of(mensagem))
                        .details(Map.of("paths", paths))
                        .build()
                );
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
