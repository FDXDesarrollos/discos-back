package net.fdxdesarrollos.albums.security.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Privilegio implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String email;	
	private String usuario;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;	
	
	public Privilegio(String nombre, String email, String usuario, String password, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.usuario = usuario;
		this.password = password;
		this.authorities = authorities;
	}
	
	public static Privilegio build( Usuario usuario ) {
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre().name()))
				.collect(Collectors.toList());
		
		return new Privilegio(usuario.getNombre(),
				              usuario.getEmail(),
				              usuario.getUsuario(),
				              usuario.getPassword(),
				              authorities);
	}
	
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }	
	
	public String getNombre() {
		return nombre;
	}

	public String getEmail() {
		return email;
	}
	
	@Override
	public String getUsername() {
		return usuario;
	}	
	
	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
