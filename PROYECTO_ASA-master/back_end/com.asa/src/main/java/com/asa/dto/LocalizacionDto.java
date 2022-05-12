package com.asa.dto;

import javax.validation.constraints.NotEmpty;

public class LocalizacionDto {

	private Long id;

	@NotEmpty(message = "no puede estar vacío!")
	private String nombre;

	@NotEmpty(message = "no puede estar vacío!")
	private String direccion;

	@NotEmpty(message = "no puede estar vacío!")
	private String localidad;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

}
