package net.fdxdesarrollos.albums.security.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class NewUser implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "El nombre es requerido")
	private String nombre;
	
	@Email(message = "Formato de correo invalido")
	@NotBlank(message = "El email es requerido")
	private String email;
	
	@NotBlank(message = "El usuario es requerido")
	private String usuario;
	
	@NotBlank(message = "El password es requerido")
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
