package com.asa.CRUD.dto;

import java.time.LocalDateTime;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EventoDto {

	private Long id;

	@NotNull(message = "no puede estar vacío!")
	private Date fecha;

	@NotEmpty(message = "no puede estar vacío!")
	private String descripcion;

	@NotEmpty(message = "no puede estar vacío!")
	private String nombre;



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
