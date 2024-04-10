package net.fdxdesarrollos.albums.security.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginUser {
	@NotBlank(message = "Usuario/Email es requerido")
	private String usuario;
	
	@NotBlank(message = "Contraseña es requerido")
	private String password;

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
	
	
}
