package net.fdxdesarrollos.albums.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.fdxdesarrollos.albums.security.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Optional<Usuario> findByUsuario(String usuario);
	boolean existsByUsuario(String usuario);
	boolean existsByEmail(String email);
}
