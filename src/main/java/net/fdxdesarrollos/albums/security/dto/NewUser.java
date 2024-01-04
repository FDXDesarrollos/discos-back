package net.fdxdesarrollos.albums.security.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NewUser implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	@NotNull
	private String nombre;
	
	@NotEmpty
	@NotNull
	@Email
	private String email;
	
	@NotEmpty
	@NotNull
	private String usuario;
	
	@NotEmpty
	@NotNull
	private String password;
	
	private Set<String> roles = new HashSet<>();

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	
	
}
