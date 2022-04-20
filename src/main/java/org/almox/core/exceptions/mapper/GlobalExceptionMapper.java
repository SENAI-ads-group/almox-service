package org.almox.core.exceptions.mapper;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.almox.core.exceptions.ApplicationRuntimeException;
import org.almox.core.exceptions.EntidadeNaoEncontradaException;
import org.almox.core.exceptions.RestException;
import org.almox.core.exceptions.ViolacaoIntegridadeDadosException;
import org.almox.core.rest.ErroPadraoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
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

    public static final String ERRO_ENTIDADE_NAO_ENCONTRADA = "Entidade não encontrada";
    public static final String ERRO_ENTIDADE_METHOD_ARGUMENT_NOT_VALID = "Parâmetro(s) inválido";
    public static final String ERRO_APPLICATION_RUNTIME_EXCEPTION = "Erro inesperado";
    public static final String ERRO_VIOLACAO_INTEGRIDADE_DADOS = "A operação solicitada viola a integridade dos dados.";

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErroPadraoDTO> constraintViolationException(ConstraintViolationException ex) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        Map<String, List<String>> details = getContraintViolationErrors(ex);
        return ResponseEntity
                .status(status)
                .body(ErroPadraoDTO.builder()
                        .status(status.value())
                        .date(LocalDateTime.now().toString())
                        .exception(ex.getClass().getSimpleName())
                        .error(status.getReasonPhrase())
                        .details(details)
                        .build()
                );
    }

    @ExceptionHandler(RestException.class)
    public ResponseEntity<ErroPadraoDTO> restException(RestException ex, HttpServletRequest servletRequest) {
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getErroDTO());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErroPadraoDTO> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest servletRequest) {
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

    @ExceptionHandler(ApplicationRuntimeException.class)
    public ResponseEntity<ErroPadraoDTO> applicationRuntimeException(ApplicationRuntimeException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getErroDTO());
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<ErroPadraoDTO> entidadeNaoEncontradaException(EntidadeNaoEncontradaException e, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(httpStatus).body(
                ErroPadraoDTO.builder()//
                        .exception(e.getClass().getName())//
                        .messages(List.of(e.getMessage()))//
                        .date(LocalDateTime.now().toString())//
                        .status(httpStatus.value())//
                        .error(ERRO_ENTIDADE_NAO_ENCONTRADA)//
                        .build()
        );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErroPadraoDTO> entityNotFoundException(EntityNotFoundException e, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(httpStatus).body(
                ErroPadraoDTO.builder()//
                        .exception(e.getClass().getName())//
                        .messages(List.of(e.getMessage()))//
                        .date(LocalDateTime.now().toString())//
                        .status(httpStatus.value())//
                        .error(ERRO_ENTIDADE_NAO_ENCONTRADA)//
                        .build()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroPadraoDTO> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        List<String> mensagensErro = e.getAllErrors().stream()//
                .map(ObjectError::getDefaultMessage)// Mensagem definida no messages.properties
                .collect(Collectors.toList());

        return ResponseEntity.status(httpStatus).body(
                ErroPadraoDTO.builder()//
                        .exception(e.getClass().getName())//
                        .messages(mensagensErro)//
                        .date(LocalDateTime.now().toString())//
                        .status(httpStatus.value())//
                        .error(ERRO_ENTIDADE_METHOD_ARGUMENT_NOT_VALID)//
                        .build()
        );
    }

    @ExceptionHandler(ViolacaoIntegridadeDadosException.class)
    public ResponseEntity<ErroPadraoDTO> violacaoIntegridadeDadosException(ViolacaoIntegridadeDadosException e, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(httpStatus).body(
                ErroPadraoDTO.builder()//
                        .exception(e.getClass().getName())//
                        .messages(List.of(e.getMessage()))//
                        .date(LocalDateTime.now().toString())//
                        .status(httpStatus.value())//
                        .error(ERRO_VIOLACAO_INTEGRIDADE_DADOS)//
                        .build()
        );
    }

    public static Map<String, List<String>> getContraintViolationErrors(ConstraintViolationException e) {
        Map<String, List<ConstraintViolation<?>>> mapaErrosPorCampo = e.getConstraintViolations().stream()
                .filter(c -> c.getPropertyPath().toString() != null)
                .collect(Collectors.groupingBy(c -> c.getPropertyPath().toString()));

        Map<String, List<String>> mapaDescricoesErrosPorCampo = mapaErrosPorCampo.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        (entry -> entry.getValue()
                                .stream()
                                .map(c -> c.getInvalidValue() instanceof List
                                        ? c.getLeafBean().toString() + " - " + c.getMessage()
                                        : c.getMessage()
                                )
                                .collect(Collectors.toList())
                        )
                ));
        return mapaDescricoesErrosPorCampo;
    }

}
