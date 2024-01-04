package net.fdxdesarrollos.albums.security.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.fdxdesarrollos.albums.entity.Mensaje;
import net.fdxdesarrollos.albums.security.dto.JwtDto;
import net.fdxdesarrollos.albums.security.dto.LoginUser;
import net.fdxdesarrollos.albums.security.dto.NewUser;
import net.fdxdesarrollos.albums.security.entity.Rol;
import net.fdxdesarrollos.albums.security.entity.Usuario;
import net.fdxdesarrollos.albums.security.enums.Roles;
import net.fdxdesarrollos.albums.security.jwt.JwtProvider;
import net.fdxdesarrollos.albums.security.service.RolService;
import net.fdxdesarrollos.albums.security.service.UsuarioService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
	private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final RolService rolService;
    private final JwtProvider jwtProvider;	
	
	public AuthController(PasswordEncoder passwordEncoder,
			              AuthenticationManager authenticationManager,
			              UsuarioService usuarioService,
			              RolService rolService,
			              JwtProvider jwtProvider) {
		this.passwordEncoder = passwordEncoder; 
        this.authenticationManager = authenticationManager; 
        this.usuarioService = usuarioService; 
        this.rolService = rolService; 
        this.jwtProvider = jwtProvider; 	
	}
	
	@PostMapping("/nuevo")
	public ResponseEntity<?> nuevo(@Valid @RequestBody NewUser nuevoUsuario, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			Map<String, Object> response = new HashMap<>();
			
			List<String> errors = bindingResult.getFieldErrors()
					.stream()
					.map( (err) -> "El campo: '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			//return new ResponseEntity(new Mensaje("Verifique información"), HttpStatus.BAD_REQUEST);
		}
		else if(usuarioService.existsByUsuario(nuevoUsuario.getUsuario())) {
			return new ResponseEntity(new Mensaje("Usuario ya existe"), HttpStatus.BAD_REQUEST);
		}
		else if(usuarioService.existsByEmail(nuevoUsuario.getEmail())) {
			return new ResponseEntity(new Mensaje("Email ya existe"), HttpStatus.BAD_REQUEST);
		}
		else {
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
			usuarioService.save(usuario);
			
			return new ResponseEntity(new Mensaje("Usuario registrado"), HttpStatus.CREATED);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			/*for(FieldError err: bindingResult.getFieldErrors()) {
				System.out.println("El campo: '" + err.getField() + "' " + err.getDefaultMessage() );
			}*/
			
			return new ResponseEntity(new Mensaje("Usuario / Contraseña incorrectos"), HttpStatus.BAD_REQUEST);
		}
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsuario(), loginUser.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());		
		return new ResponseEntity(jwtDto, HttpStatus.OK);
	}
	
}
