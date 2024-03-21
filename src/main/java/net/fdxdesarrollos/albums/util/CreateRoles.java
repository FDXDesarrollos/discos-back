package net.fdxdesarrollos.albums.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import net.fdxdesarrollos.albums.security.entity.Rol;
import net.fdxdesarrollos.albums.security.service.RolService;


/**
 * MUY IMPORTANTE: ESTA CLASE SÓLO SE EJECUTARÁ UNA VEZ PARA CREAR LOS ROLES.
 * UNA VEZ CREADOS SE DEBERÁ ELIMINAR O BIEN COMENTAR EL CÓDIGO
 *
 **/


@Component
public class CreateRoles implements CommandLineRunner {

    /*@Autowired
    RolService rolService;*/
	
	/*@Autowired
	private BCryptPasswordEncoder passwordEncoder;*/	

    @Override
    public void run(String... args) throws Exception {
        /*Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
        Rol rolUser = new Rol(RolNombre.ROLE_USER);
        rolService.save(rolAdmin);
        rolService.save(rolUser);*/
    	
		/*String password = "admin";
		
		for(int i = 0; i < 4; i++) {
			String passwordBcrypt = passwordEncoder.encode(password);
			System.out.println("===> " + passwordBcrypt);
		}*/
    }
}