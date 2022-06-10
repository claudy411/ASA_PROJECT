package com.asa.CRUD.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ContactoDto {
	
	private Long id;
	
	@NotNull(message="no puede estar vacío!")
	private String nombre;
	
	private Date fecha;
	
	@NotNull(message="no puede estar vacío!")
	private String apellidos;
	
	@NotNull(message="no puede estar vacío!")
	@Email(message=" el formato no es válido!")
	private String email;
	
	@NotNull(message="no puede estar vacío!")
	@Size(min=9, max=9)
	private String telefono;
	
	@NotNull(message="no puede estar vacío!")
	private String mensaje;

	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
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

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	

}
