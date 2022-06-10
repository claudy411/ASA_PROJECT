package com.asa.CRUD.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.asa.CRUD.model.entity.Localizacion;
import com.asa.CRUD.model.entity.Voluntaria;

public class EventoDto {

	private Long id;

	@NotNull(message = "no puede estar vacío!")
	private Date fecha;

	@NotNull(message = "no puede estar vacío!")
	private String descripcion;

	@NotNull(message = "no puede estar vacío!")
	private String nombre;
	
	private Localizacion localizacion;
	

	public Localizacion getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(Localizacion localizacion) {
		this.localizacion = localizacion;
	}

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

}
