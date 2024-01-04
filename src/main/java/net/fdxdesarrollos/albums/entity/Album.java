package net.fdxdesarrollos.albums.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="albums")
public class Album {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	private String titulo;
	
	@NotBlank
	private String interprete;
	
	//@NotBlank
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-MX", timezone = "America/Mexico_City") 
	private Date fecha;
	
	@NotBlank
	private String genero;
	
	@Min(0)
	private Double precio;
	
	
	public Album() {
		super();
	}

	public Album(Integer id, String titulo, String interprete, Date fecha, String genero, Double precio) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.interprete = interprete;
		this.fecha = fecha;
		this.genero = genero;
		this.precio = precio;
	}	

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getInterprete() {
		return interprete;
	}

	public void setInterprete(String interprete) {
		this.interprete = interprete;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Album [id=" + id + ", titulo=" + titulo + ", interprete=" + interprete + ", fecha=" + fecha
				+ ", genero=" + genero + ", precio=" + precio + "]";
	}

}
