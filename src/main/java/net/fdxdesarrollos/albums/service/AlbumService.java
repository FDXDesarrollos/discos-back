package net.fdxdesarrollos.albums.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import net.fdxdesarrollos.albums.dto.AlbumDto;
import net.fdxdesarrollos.albums.dto.Mensaje;
import net.fdxdesarrollos.albums.exceptions.CustomException;
import net.fdxdesarrollos.albums.repository.AlbumRepository;

@Service
@Transactional
public class AlbumService {
	@Autowired
	AlbumRepository albumRepo;
	
	public List<AlbumDto> list(){
		return albumRepo.findAll();
	}
	
	public AlbumDto getOne(int id){
		return albumRepo.findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Album no encontrado"));
	}

	public AlbumDto getByTitulo(String titulo){
		return albumRepo.findByTitulo(titulo).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Album no econtrado"));
	}
	
	public boolean existsById(int id) {
		return albumRepo.existsById(id);
	}
	
	public boolean existsByTitulo(String titulo) {
		return albumRepo.existsByTitulo(titulo);
	}
	
	public Mensaje save(AlbumDto dto) {
		if(albumRepo.existsByTitulo(dto.getTitulo()))
			throw new CustomException(HttpStatus.BAD_REQUEST, "El titulo ya existe");
		
		albumRepo.save(dto);
		return new Mensaje("Información registrada");
	}
	
	public Mensaje update(int id, AlbumDto dto) {
		if(!albumRepo.existsById(id))
			throw new CustomException(HttpStatus.NOT_FOUND, "Album no encontrado");	

		Optional<AlbumDto> opt = albumRepo.findByTitulo(dto.getTitulo()); 
		if(opt.isPresent() && opt.get().getId() != id)
			throw new CustomException(HttpStatus.NOT_FOUND, "El titulo ya existe");
		
		albumRepo.save(dto);
		return new Mensaje("Información actualizada");
	}
	
	public Mensaje delete(int id) {
		if(!albumRepo.existsById(id))
			throw new CustomException(HttpStatus.NOT_FOUND, "Album no encontrado");
		
		albumRepo.deleteById(id);
		return new Mensaje("Información eliminada");
	}	
}
