package com.asa.CRUD.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.asa.CRUD.model.entity.Evento;

public class VoluntariaDto {

	private Long id;

	@NotEmpty(message = "no puede estar vacío!")
	private String nombre;

	@NotEmpty(message = "no puede estar vacío!")
	private String apellido1;

	private String apellido2;

	@NotEmpty(message = "no puede estar vacío!")
	@Email(message = " Email incorrecto! ")
	private String email;

	@NotEmpty(message = "Nº de telefono incorrecto!")
	@Size(min=9, max=9,message = " Nº de telefono incorrecto!")
	private String telefono;

	@NotEmpty(message = "no puede estar vacío!")
	private String direccion;

	@NotEmpty(message = "no puede estar vacío!")
	private String ciudad;
	
	@NotEmpty(message = "no puede estar vacío!")
	private String tareas;

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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getTareas() {
		return tareas;
	}

	public void setTareas(String tareas) {
		this.tareas = tareas;
	}

}
