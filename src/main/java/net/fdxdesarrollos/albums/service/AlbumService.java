package net.fdxdesarrollos.albums.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fdxdesarrollos.albums.entity.Album;
import net.fdxdesarrollos.albums.repository.AlbumRepository;

@Service
@Transactional
public class AlbumService {
	@Autowired
	AlbumRepository albumRepo;
	
	public List<Album> list(){
		return albumRepo.findAll();
	}
	
	public Optional<Album> getOne(int id){
		return albumRepo.findById(id);
	}

	public Optional<Album> getByTitulo(String titulo){
		return albumRepo.findByTitulo(titulo);
	}
	
	public void save(Album album) {
		albumRepo.save(album);
	}
	
	public void delete(int id) {
		albumRepo.deleteById(id);
	}
	
	public boolean existsById(int id) {
		return albumRepo.existsById(id);
	}
	
	public boolean existsByTitulo(String titulo) {
		return albumRepo.existsByTitulo(titulo);
	}	
}
