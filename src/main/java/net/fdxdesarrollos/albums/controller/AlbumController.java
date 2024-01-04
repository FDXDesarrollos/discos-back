package net.fdxdesarrollos.albums.controller;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.fdxdesarrollos.albums.entity.Album;
import net.fdxdesarrollos.albums.entity.Mensaje;
import net.fdxdesarrollos.albums.service.AlbumService;

@RestController
@RequestMapping("/api/album")
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:4200")
public class AlbumController {
	
	@Autowired
	private AlbumService albumService;
	
	@GetMapping("/listar")
	public ResponseEntity<List <Album>> list() {
		List<Album> list = albumService.list();
		return new ResponseEntity<List<Album>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/detalle/{id}")
	public ResponseEntity<Album> getById(@PathVariable("id") int id) {
		if(!albumService.existsById(id))
			return new ResponseEntity(new Mensaje("Album no encontrado"), HttpStatus.NOT_FOUND);
		
		Album album = albumService.getOne(id).get();
		
		return new ResponseEntity<Album>(album, HttpStatus.OK);
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<Album> getByTitulo(@PathVariable("titulo") String titulo) {
		if(!albumService.existsByTitulo(titulo))
			return new ResponseEntity(new Mensaje("Album no encontrado"), HttpStatus.NOT_FOUND);
		
		Album album = albumService.getByTitulo(titulo).get();
		return new ResponseEntity<Album>(album, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/agregar")
	public ResponseEntity<?> create(@RequestBody Album album) {
		if(StringUtils.isBlank(album.getTitulo()))
			return new ResponseEntity(new Mensaje("El titulo es obligatorio"), HttpStatus.BAD_REQUEST);
		
		if(albumService.existsByTitulo(album.getTitulo()))
			return new ResponseEntity(new Mensaje("El titulo ya existe"), HttpStatus.BAD_REQUEST);		
		
		if(Objects.isNull(album.getPrecio()) || album.getPrecio() < 0)
			return new ResponseEntity(new Mensaje("El precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
		
		albumService.save(album);
		return new ResponseEntity(new Mensaje("Información registrada"), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Album album) {
		if(!albumService.existsById(id))
			return new ResponseEntity(new Mensaje("Album no encontrado"), HttpStatus.NOT_FOUND);		
		
		if(StringUtils.isBlank(album.getTitulo()))
			return new ResponseEntity(new Mensaje("El titulo es obligatorio"), HttpStatus.BAD_REQUEST);
		
		if(albumService.existsByTitulo(album.getTitulo()) && albumService.getByTitulo(album.getTitulo()).get().getId() != id)
			return new ResponseEntity(new Mensaje("El titulo ya existe"), HttpStatus.BAD_REQUEST);		
		
		if(Objects.isNull(album.getPrecio()) || album.getPrecio() < 0)
			return new ResponseEntity(new Mensaje("El precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
		
		albumService.save(album);
		return new ResponseEntity(new Mensaje("Información actualizada"), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		if(!albumService.existsById(id))
			return new ResponseEntity(new Mensaje("Album no encontrado"), HttpStatus.NOT_FOUND);
		
		albumService.delete(id);
		return new ResponseEntity(new Mensaje("Información eliminada"), HttpStatus.OK);
	}
}
