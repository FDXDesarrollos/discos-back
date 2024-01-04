package net.fdxdesarrollos.albums.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import net.fdxdesarrollos.albums.entity.Album;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
	Optional <Album> findByTitulo(String titulo);
	boolean  existsByTitulo(String titulo);
}
