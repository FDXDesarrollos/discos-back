package net.fdxdesarrollos.albums.security.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import net.fdxdesarrollos.albums.security.enums.Roles;


@Entity
@Table(name="roles")
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "nombre")
	private Roles nombre;
	
	public Rol() {
		
	}

	public Rol(Roles nombre) {
		super();
		this.nombre = nombre;
	}
	
	/*public Rol(Integer id, Roles rolNombre) {
		super();
		this.id = id;
		this.rolNombre = rolNombre;
	}*/

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Roles getNombre() {
		return nombre;
	}

	public void setNombre(Roles nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Rol [id=" + id + ", rolNombre=" + nombre + "]";
	}
	

}
