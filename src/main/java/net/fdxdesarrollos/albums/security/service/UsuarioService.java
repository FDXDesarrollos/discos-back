package net.fdxdesarrollos.albums.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.fdxdesarrollos.albums.security.entity.Usuario;
import net.fdxdesarrollos.albums.security.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	
	public Optional<Usuario> getByUsuario(String usuario){
		return usuarioRepository.findByUsuario(usuario);
	}
	
	public boolean existsByUsuario(String usuario) {
		return usuarioRepository.existsByUsuario(usuario);
	}
	
	public boolean existsByEmail(String email) {
		return usuarioRepository.existsByEmail(email);
	}
	
	public void save (Usuario usuario) {
		usuarioRepository.save(usuario);
	}

}
