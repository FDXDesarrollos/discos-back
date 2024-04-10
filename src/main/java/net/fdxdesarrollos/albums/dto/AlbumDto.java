package net.fdxdesarrollos.albums.dto;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="albums")
public class AlbumDto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "El titulo es obligatorio")
	private String titulo;
	
	@NotBlank(message = "El interprete es obligatorio")
	private String interprete;
	
	//@NotBlank
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-MX", timezone = "America/Mexico_City") 
	private Date fecha;
	
	@NotBlank(message = "El genero musical es obligatorio")
	private String genero;
	
	@Min(value = 0, message = "El precio es obligatorio")
	private Double precio;
	
	
	public AlbumDto() {
		super();
	}

	public AlbumDto(Integer id, String titulo, String interprete, Date fecha, String genero, Double precio) {
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
