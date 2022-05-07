package com.asa.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="encargados_localizaciones")
public class EncargadoLocalizacion implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="no puede estar vacío!")
	private String nombre;
	
	@NotEmpty(message="no puede estar vacío!")
	private String apellido1;
	
	
	private String apellido2;
	
	@NotEmpty(message="no puede estar vacío!")
	@Email(message=" el formato no es válido!")
	@Column(nullable = false, unique = true)
	private String email;
	
	@NotEmpty(message="no puede estar vacío!")
	private String telefono;

	@ManyToOne
	@JoinColumn(name = "id_localizacion", nullable = false)
	private Localizacion localizacion;
	
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



	public void setApellido1(String apellido) {
		this.apellido1 = apellido;
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



	/**
	 * 
	 */
	private static final long serialVersionUID = 3260364628495270393L;

}
