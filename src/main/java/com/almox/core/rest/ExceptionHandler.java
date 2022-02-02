package com.almox.core.rest;

import com.almox.core.exceptions.ApplicationRuntimeException;
import com.almox.core.exceptions.EntidadeNaoEncontradaException;
import com.almox.core.exceptions.ViolacaoIntegridadeDadosException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandler {

    public static final String ERRO_ENTIDADE_NAO_ENCONTRADA = "Entidade não encontrada";
    public static final String ERRO_ENTIDADE_METHOD_ARGUMENT_NOT_VALID = "Parâmetro(s) inválido";
    public static final String ERRO_APPLICATION_RUNTIME_EXCEPTION = "Erro inesperado";
    public static final String ERRO_VIOLACAO_INTEGRIDADE_DADOS = "A operação solicitada viola a integridade dos dados.";

    @org.springframework.web.bind.annotation.ExceptionHandler(ApplicationRuntimeException.class)
    public ResponseEntity<ErroPadraoDTO> applicationRuntime(ApplicationRuntimeException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getErroDTO());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<ErroPadraoDTO> entidadeNaoEncontrada(EntidadeNaoEncontradaException e, HttpServletRequest request) {
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

    @org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErroPadraoDTO> entidadeNaoEncontrada(EntityNotFoundException e, HttpServletRequest request) {
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

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroPadraoDTO> argumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
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

    @org.springframework.web.bind.annotation.ExceptionHandler(ViolacaoIntegridadeDadosException.class)
    public ResponseEntity<ErroPadraoDTO> applicationRuntime(ViolacaoIntegridadeDadosException e, HttpServletRequest request) {
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
}
