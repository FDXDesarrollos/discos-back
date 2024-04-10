package net.fdxdesarrollos.albums.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


/**
 * MUY IMPORTANTE: ESTA CLASE SÓLO SE EJECUTARÁ UNA VEZ PARA CREAR LOS ROLES.
 * UNA VEZ CREADOS SE DEBERÁ ELIMINAR O BIEN COMENTAR EL CÓDIGO
 *
 **/


@Component
public class CreateRoles implements CommandLineRunner {

    /*@Autowired
    RolService rolService;*/
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;	

    @Override
    public void run(String... args) throws Exception {
        /*
        Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
        Rol rolUser = new Rol(RolNombre.ROLE_USER);
        rolService.save(rolAdmin);
        rolService.save(rolUser);
        */
    	
    	/*
		String password = "admin";
		
		for(int i = 0; i < 4; i++) {
			String passwordBcrypt = passwordEncoder.encode(password);
			System.out.println("===> " + passwordBcrypt);
		}
		*/
    	
    	/*
    	SecureRandom random = new SecureRandom();
    	byte[] bytes = new byte[36];	// 36 bytes * 8 = 288 bits, a little bit more than the 256 required bits 
    	random.nextBytes(bytes);
    	var encoder = Base64.getUrlEncoder().withoutPadding();
    	System.out.println("===> " + encoder.encodeToString(bytes));
    	*/
    }
}

