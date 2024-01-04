package net.fdxdesarrollos.albums.security.dto;

import javax.validation.constraints.NotEmpty;

public class LoginUser {
	@NotEmpty
	private String usuario;
	
	@NotEmpty
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
