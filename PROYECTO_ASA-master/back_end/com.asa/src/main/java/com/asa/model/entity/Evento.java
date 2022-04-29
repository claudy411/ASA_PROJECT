package com.asa.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="eventos")
public class Evento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4201748380504716352L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="no puede estar vacío!")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@NotEmpty(message="no puede estar vacío!")
	private String descripcion;
	
	@NotEmpty(message="no puede estar vacío!")
	private String nombre;
	
//	@NotEmpty(message="no puede estar vacío!")
//	private Localizacion localizacion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

//	public Localizacion getLocalizacion() {
//		return localizacion;
//	}
//
//	public void setLocalizacion(Localizacion localizacion) {
//		this.localizacion = localizacion;
//	}


	
	
}
