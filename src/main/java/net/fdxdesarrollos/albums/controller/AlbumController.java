package net.fdxdesarrollos.albums.controller;

import java.util.List;
import java.util.Objects;

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

import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import net.fdxdesarrollos.albums.dto.AlbumDto;
import net.fdxdesarrollos.albums.dto.Mensaje;
import net.fdxdesarrollos.albums.service.AlbumService;

@RestController
@RequestMapping("/api/album")
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:4200")
public class AlbumController {
	
	@Autowired
	private AlbumService albumService;
	
	@GetMapping("/listar")
	public ResponseEntity<List <AlbumDto>> list() {
		return ResponseEntity.ok( albumService.list() );
	}
	
	@GetMapping("/detalle/{id}")
	public ResponseEntity<AlbumDto> getById(@PathVariable("id") int id) {
		return ResponseEntity.ok( albumService.getOne(id) );
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<AlbumDto> getByTitulo(@PathVariable("titulo") String titulo) {
		return ResponseEntity.ok( albumService.getByTitulo(titulo) );
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/agregar")
	public ResponseEntity<Mensaje> create(@RequestBody AlbumDto dto) {
		return ResponseEntity.ok(albumService.save(dto));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Mensaje> update(@PathVariable("id") int id, @Valid @RequestBody AlbumDto dto) {
		return ResponseEntity.ok(albumService.update(id, dto));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Mensaje> delete(@PathVariable("id") int id) {		
		return ResponseEntity.ok(albumService.delete(id));
	}
}
