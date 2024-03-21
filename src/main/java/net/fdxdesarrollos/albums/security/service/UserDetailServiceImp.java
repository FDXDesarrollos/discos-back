package net.fdxdesarrollos.albums.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.fdxdesarrollos.albums.security.entity.Usuario;
import net.fdxdesarrollos.albums.security.entity.Privilegio;

@Service
public class UserDetailServiceImp implements UserDetailsService {
	
	@Autowired
	UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String usuariOrEmail) throws UsernameNotFoundException {
		Usuario user = usuarioService.getByUsuarioOrEmail(usuariOrEmail).get();
		return Privilegio.build(user);
	}
	
	
	
}
