package net.fdxdesarrollos.albums.security.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import net.fdxdesarrollos.albums.dto.Mensaje;
import net.fdxdesarrollos.albums.security.dto.JwtDto;
import net.fdxdesarrollos.albums.security.dto.LoginUser;
import net.fdxdesarrollos.albums.security.dto.NewUser;
import net.fdxdesarrollos.albums.security.service.UsuarioService;


@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
	@Autowired
	UsuarioService usuarioService;
	
	
	@PostMapping("/nuevo")
	public ResponseEntity<Mensaje> nuevo(@Valid @RequestBody NewUser nuevoUsuario) {
		return ResponseEntity.ok(usuarioService.save(nuevoUsuario));
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser) {	
		return ResponseEntity.ok(usuarioService.login(loginUser));
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<JwtDto> refresh(@RequestBody JwtDto jwtDto) {
		return ResponseEntity.ok(usuarioService.refresh(jwtDto));
	}
	
	
}
