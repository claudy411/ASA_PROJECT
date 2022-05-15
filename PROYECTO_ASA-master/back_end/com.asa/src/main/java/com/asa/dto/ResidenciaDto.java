package com.asa.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class ResidenciaDto {

	private Long id;

	@NotEmpty(message = "no puede estar vacío!")
	private String nombre;

	@NotEmpty(message = "no puede estar vacío!")
	private String precio;

	@NotEmpty(message = "no puede estar vacío!")
	private String telefono;

	@NotEmpty(message = "no puede estar vacío!")
	private String propietario;

	@NotEmpty(message = "no puede estar vacío!")
	private String calle;

	@NotEmpty(message = "no puede estar vacío!")
	private String numero;

	@NotEmpty(message = "no puede estar vacío!")
	private String cp;

	@NotEmpty(message = "no puede estar vacío!")
	private String localidad;
	
	@NotEmpty(message = "no puede estar vacío!")
	@Email(message = " el formato no es válido!")
	private String email;

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

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPropietario() {
		return propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}
