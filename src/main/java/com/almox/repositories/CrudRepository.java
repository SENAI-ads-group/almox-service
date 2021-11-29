package com.almox.repositories;

import com.almox.model.entidades.EntidadePadrao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CrudRepository<T extends EntidadePadrao> extends JpaRepository<T, Long> {
}
