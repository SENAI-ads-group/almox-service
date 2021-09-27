package com.almox.services;

import com.almox.exceptions.ApplicationRuntimeException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface ICrudService<T, F> {
    default T buscarPorId(long id) {
        throw new ApplicationRuntimeException(HttpStatus.NOT_IMPLEMENTED);
    }

    default List<T> buscarTodos(F filtro) {
        throw new ApplicationRuntimeException(HttpStatus.NOT_IMPLEMENTED);
    }

    default List<T> buscarTodos() {
        throw new ApplicationRuntimeException(HttpStatus.NOT_IMPLEMENTED);
    }

    default Page<T> buscarPaginado() {
        throw new ApplicationRuntimeException(HttpStatus.NOT_IMPLEMENTED);
    }

    default T criar(T entidade) {
        throw new ApplicationRuntimeException(HttpStatus.NOT_IMPLEMENTED);
    }

    default T atualizar(Long id, T entidade) {
        throw new ApplicationRuntimeException(HttpStatus.NOT_IMPLEMENTED);
    }

    default void excluir(long id) {
        throw new ApplicationRuntimeException(HttpStatus.NOT_IMPLEMENTED);
    }
}
