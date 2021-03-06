package com.asa.CRUD.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.asa.CRUD.model.entity.Localizacion;

public class EncargadoDto {

	private Long id;

	@NotEmpty(message = "no puede estar vacío!")
	private String nombre;

	@NotEmpty(message = "no puede estar vacío!")
	private String apellido1;

	private String apellido2;

	@NotEmpty(message = "no puede estar vacío!")
	@Email(message = " el formato no es válido!")
	private String email;

	@NotEmpty(message = "no puede estar vacío!")
	private String telefono;

	
	private LocalizacionDto localizacion;



	public LocalizacionDto getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(LocalizacionDto localizacion) {
		this.localizacion = localizacion;
	}

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

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
