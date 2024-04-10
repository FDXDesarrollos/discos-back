package net.fdxdesarrollos.albums.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.fdxdesarrollos.albums.security.entity.Privilegio;
import net.fdxdesarrollos.albums.security.entity.Usuario;
import net.fdxdesarrollos.albums.security.repository.UsuarioRepository;

@Service
public class UserDetailServiceImp implements UserDetailsService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String usuariOrEmail) throws UsernameNotFoundException {
		Usuario user = usuarioRepository.findByUsuarioOrEmail(usuariOrEmail, usuariOrEmail)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
		return Privilegio.build(user);
	}
	
	
	
}
