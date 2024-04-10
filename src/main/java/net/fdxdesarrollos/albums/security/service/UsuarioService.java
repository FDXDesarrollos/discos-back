package net.fdxdesarrollos.albums.security.service;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.fdxdesarrollos.albums.dto.Mensaje;
import net.fdxdesarrollos.albums.exceptions.CustomException;
import net.fdxdesarrollos.albums.security.dto.JwtDto;
import net.fdxdesarrollos.albums.security.dto.LoginUser;
import net.fdxdesarrollos.albums.security.dto.NewUser;
import net.fdxdesarrollos.albums.security.entity.Rol;
import net.fdxdesarrollos.albums.security.entity.Usuario;
import net.fdxdesarrollos.albums.security.enums.Roles;
import net.fdxdesarrollos.albums.security.jwt.JwtProvider;
import net.fdxdesarrollos.albums.security.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
    
	@Autowired
	AuthenticationManager authenticationManager; 
    
	@Autowired
	RolService rolService;
    
	@Autowired
	JwtProvider jwtProvider;	
	
	
	public Optional<Usuario> getByUsuario(String usuario){
		return usuarioRepository.findByUsuario(usuario);
	}
	
	public Optional<Usuario> getByUsuarioOrEmail(String usuariOrEmail){
		return usuarioRepository.findByUsuarioOrEmail(usuariOrEmail, usuariOrEmail);
	}
	
	public Optional<Usuario> getByTokenPassword(String tokenPassword){
	    return usuarioRepository.findByTokenPassword(tokenPassword);
	}	
	
	public boolean existsByUsuario(String usuario) {
		return usuarioRepository.existsByUsuario(usuario);
	}
	
	public boolean existsByEmail(String email) {
		return usuarioRepository.existsByEmail(email);
	}
	
	/**************************************************/
	
	public JwtDto login(LoginUser loginUser) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsuario(), loginUser.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		return new JwtDto(jwt);
	}
	
	public JwtDto refresh(JwtDto jwtDto) {
		String token = "";
		
		try {
			token = jwtProvider.refreshToken(jwtDto);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return new JwtDto(token);
	}
	
	public Mensaje save (NewUser nuevoUsuario) {
		if(usuarioRepository.existsByUsuario(nuevoUsuario.getUsuario())) 
			throw new CustomException(HttpStatus.BAD_REQUEST, "Usuario ya existe");
		
		if(usuarioRepository.existsByEmail(nuevoUsuario.getEmail())) 
			throw new CustomException(HttpStatus.BAD_REQUEST, "Email ya existe");
		
		Usuario usuario = new Usuario(nuevoUsuario.getNombre(),
									  nuevoUsuario.getEmail(),                      
				                      nuevoUsuario.getUsuario(),
				                      passwordEncoder.encode(nuevoUsuario.getPassword()));
		
		Set<Rol> roles = new HashSet<>();
		roles.add(rolService.getByNombre(Roles.ROLE_USER).get());
		
		if(nuevoUsuario.getRoles().contains("admin")) {
			roles.add(rolService.getByNombre(Roles.ROLE_ADMIN).get());
		}
		
		usuario.setRoles(roles);	
		usuarioRepository.save(usuario);
		
		return new Mensaje(usuario.getNombre() + " ha sido creado");
	}

}
