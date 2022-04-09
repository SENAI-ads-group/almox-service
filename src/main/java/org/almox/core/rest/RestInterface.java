package org.almox.core.rest;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

public interface RestInterface {

    default URI getUriCriado(UUID id) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }

    default Sort getOrdenacao(String[] sort) {
        return Sort.by(sort);
    }

    default Pageable criarPaginacao(Optional<Integer> page, Optional<Integer> size, String[] sort) {
        return PageRequest.of(page.orElse(0), size.orElse(15), getOrdenacao(sort));
    }

    default Pageable criarPaginacao(Optional<Integer> page, Optional<Integer> size, Sort sort) {
        return PageRequest.of(page.orElse(0), size.orElse(15), sort);
    }
}
