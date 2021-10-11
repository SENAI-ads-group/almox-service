package com.almox.repositorios;

import com.almox.model.entidades.Usuario;
import com.almox.model.enums.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryCustom {

    List<Usuario> findAllByTipoUsuario(TipoUsuario tipoUsuario);

    List<Usuario> findAllByTipoUsuarioIn(Set<TipoUsuario> tiposAConsiderar);

    Usuario findByCriadoPor(Usuario usuario);

    Usuario findByEmail(String email);
}
