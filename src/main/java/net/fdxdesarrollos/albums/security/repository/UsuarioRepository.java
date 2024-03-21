package net.fdxdesarrollos.albums.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.fdxdesarrollos.albums.security.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Optional<Usuario> findByUsuario(String usuario);
	Optional<Usuario> findByUsuarioOrEmail(String usuario, String email);
	Optional<Usuario> findByTokenPassword(String tokenPassword);
	boolean existsByUsuario(String usuario);
	boolean existsByEmail(String email);
}
