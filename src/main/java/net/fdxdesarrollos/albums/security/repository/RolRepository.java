package net.fdxdesarrollos.albums.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.fdxdesarrollos.albums.security.entity.Rol;
import net.fdxdesarrollos.albums.security.enums.Roles;


@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>  {
	Optional<Rol> findByNombre(Roles nombre);
}
