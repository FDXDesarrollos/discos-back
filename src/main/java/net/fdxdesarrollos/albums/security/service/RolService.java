package net.fdxdesarrollos.albums.security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fdxdesarrollos.albums.security.entity.Rol;
import net.fdxdesarrollos.albums.security.enums.Roles;
import net.fdxdesarrollos.albums.security.repository.RolRepository;

@Service
@Transactional
public class RolService {
	
	@Autowired
	RolRepository rolRepository;
	
	public Optional<Rol> getByNombre(Roles nombre){
		return rolRepository.findByNombre(nombre);
	}
	
	public void save(Rol rol) {
		rolRepository.save(rol);
	}

}
