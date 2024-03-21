package net.fdxdesarrollos.albums.recovery.controller;

import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.fdxdesarrollos.albums.entity.Mensaje;
import net.fdxdesarrollos.albums.recovery.Dto.ChangePasswordDTO;
import net.fdxdesarrollos.albums.recovery.Dto.EmailValuesDTO;
import net.fdxdesarrollos.albums.recovery.Service.EmailService;
import net.fdxdesarrollos.albums.security.entity.Usuario;
import net.fdxdesarrollos.albums.security.service.UsuarioService;

@RestController
@RequestMapping("/recovery")
@CrossOrigin
public class RecoveryController {
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
    PasswordEncoder passwordEncoder;	
	
	@Value("${spring.mail.username}")
	private String mailFrom;
	
	private static final String subject = "Cambio de Contraseña";
	
	@PostMapping("/send-email")
	public ResponseEntity<?> sendMailTemplate(@RequestBody EmailValuesDTO dto) {
		Optional<Usuario> usuariOpt = usuarioService.getByUsuarioOrEmail(dto.getMailTo());
		if(!usuariOpt.isPresent()) {
			return new ResponseEntity(new Mensaje("Ningún usuario con esas credenciales"), HttpStatus.NOT_FOUND);
		}
		
		Usuario usuario = usuariOpt.get();
		
		dto.setMailFrom(mailFrom);
		dto.setMailTo(usuario.getEmail());
		dto.setUserName(usuario.getNombre());
		dto.setSubject(subject);
		
		UUID uuid = UUID.randomUUID();
		String tokenPassword = uuid.toString();
		dto.setTokenPassword(tokenPassword);
		usuario.setTokenPassword(tokenPassword);
		
		usuarioService.save(usuario);
		emailService.sendEmail(dto);
		return new ResponseEntity(new Mensaje("Te hemos enviado un correo"), HttpStatus.OK);
	}
	
    @PostMapping("/change-pass")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDTO dto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("Campos mal puestos"), HttpStatus.BAD_REQUEST);
        }
        
        if(!dto.getPassword().equals(dto.getConfirmPassword())) {
            return new ResponseEntity(new Mensaje("Las contraseñas no coinciden"), HttpStatus.BAD_REQUEST);
        }
        
        Optional<Usuario> usuarioOpt = usuarioService.getByTokenPassword(dto.getTokenPassword());
        if(!usuarioOpt.isPresent()) {
            return new ResponseEntity(new Mensaje("No existe ningún usuario con esas credenciales"), HttpStatus.NOT_FOUND);
        }
        
        Usuario usuario = usuarioOpt.get();
        String newPassword = passwordEncoder.encode(dto.getPassword());
        usuario.setPassword(newPassword);
        usuario.setTokenPassword(null);
        
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("Contraseña actualizada"), HttpStatus.OK);
    }	
}

