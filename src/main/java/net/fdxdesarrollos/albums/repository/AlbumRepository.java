package net.fdxdesarrollos.albums.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.fdxdesarrollos.albums.dto.AlbumDto;

public interface AlbumRepository extends JpaRepository<AlbumDto, Integer> {
	Optional <AlbumDto> findByTitulo(String titulo);
	boolean  existsByTitulo(String titulo);
}
