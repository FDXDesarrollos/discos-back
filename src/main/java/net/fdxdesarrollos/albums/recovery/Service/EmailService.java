package net.fdxdesarrollos.albums.recovery.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import net.fdxdesarrollos.albums.dto.Mensaje;
import net.fdxdesarrollos.albums.exceptions.CustomException;
import net.fdxdesarrollos.albums.recovery.Dto.ChangePasswordDTO;
import net.fdxdesarrollos.albums.recovery.Dto.EmailValuesDTO;
import net.fdxdesarrollos.albums.security.entity.Usuario;
import net.fdxdesarrollos.albums.security.repository.UsuarioRepository;

@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TemplateEngine templateEngine;

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
    PasswordEncoder passwordEncoder;	
	
	@Value("${spring.mail.username}")
	private String mailFrom;
	
    @Value("${mail.urlFront}")
    private String urlFront;	
	
	private static final String subject = "Cambio de Contraseña";
	
	public Mensaje sendMailTemplate(EmailValuesDTO dto) {
		Optional<Usuario> usuariOpt = usuarioRepository.findByUsuarioOrEmail(dto.getMailTo(), dto.getMailTo());
		
		if(!usuariOpt.isPresent())
			throw new CustomException(HttpStatus.NOT_FOUND, "Ningún usuario con esas credenciales");
		
		
		Usuario usuario = usuariOpt.get();
		
		dto.setMailFrom(mailFrom);
		dto.setMailTo(usuario.getEmail());
		dto.setUserName(usuario.getNombre());
		dto.setSubject(subject);
		
		UUID uuid = UUID.randomUUID();
		String tokenPassword = uuid.toString();
		dto.setTokenPassword(tokenPassword);
		usuario.setTokenPassword(tokenPassword);
		
		usuarioRepository.save(usuario);
		this.sendEmail(dto);		
		
		return new Mensaje("Te hemos enviado un correo");
	}
	
	public Mensaje changePassword(ChangePasswordDTO dto) {
        if(!dto.getPassword().equals(dto.getConfirmPassword())) 
        	throw new CustomException(HttpStatus.BAD_REQUEST, "Las contraseñas no coinciden");
        
        Optional<Usuario> usuarioOpt = usuarioRepository.findByTokenPassword(dto.getTokenPassword());
        if(!usuarioOpt.isPresent())
        	throw new CustomException(HttpStatus.BAD_REQUEST, "Ningún usuario con esas credenciales");
        
        Usuario usuario = usuarioOpt.get();
        String newPassword = passwordEncoder.encode(dto.getPassword());
        usuario.setPassword(newPassword);
        usuario.setTokenPassword(null);
        
        usuarioRepository.save(usuario);
        return new Mensaje("Contraseña actualizada");
	}
    
    private void sendEmail(EmailValuesDTO dto) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            Context context = new Context();
            Map<String, Object> model = new HashMap<>();
            model.put("userName", dto.getUserName());
            model.put("url", urlFront + dto.getTokenPassword());
            context.setVariables(model);
            String htmlText = templateEngine.process("email-template", context);
            helper.setFrom(dto.getMailFrom());
            helper.setTo(dto.getMailTo());
            helper.setSubject(dto.getSubject());
            helper.setText(htmlText, true);

            javaMailSender.send(message);
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }
}

